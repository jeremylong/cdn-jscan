/*
 *  Copyright 2022 Jeremy Long
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.github.jeremylong.cdnjs.storage;

import io.github.jeremylong.cdnjs.storage.utils.PathUtil;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBStore implements AutoCloseable {

    private final static String CONNECTION_STRING_ENV = "CDNJS_CONNECTION_STRING";
    private final static String DEFAULT_CONNECTION_STRING = "jdbc:h2:file:%s/data/cdnjs;AUTOCOMMIT=ON;";
    private final static String DB_USER = "CDNJS_CONNECTION_STRING";
    private final static String DEFAULT_DB_USER = "cdnjs_user";
    private final static String DB_PASSWORD = "CDNJS_DB_PASS";
    private final static String DEFAULT_DB_PASSWORD = "$3>8-K3y";
    private final static String DB_DRIVER = "CDNJS_DB_DRIVER";
    private final static String DEFAULT_DB_DRIVER = "org.h2.Driver";
    private final static String DB_DRIVER_PATH = "CDNJS_DB_DRIVER_PATH";
    /**
     * The logger.
     */
    private final Logger LOGGER = LoggerFactory.getLogger(DBStore.class);
    /**
     * The database driver used to connect to the database.
     */
    private Driver driver = null;
    private java.sql.Connection connection;

    public DBStore() throws DatabaseException, SQLException {
        // Properties properties = new Properties();
        connection = openConnection();
        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
        try (Liquibase liquibase = new liquibase.Liquibase(
                "db.changelog.xml",
                new ClassLoaderResourceAccessor(),
                database)) {
            // properties.forEach((key, value) -> liquibase.setChangeLogParameter(Objects.toString(key), value));
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
    }

    private static String getSetting(String key, String defaultValue) {
        String connectionString = System.getenv(key);
        if (connectionString == null) {
            connectionString = System.getProperty(key, defaultValue);
        }
        return connectionString;
    }

    private Connection openConnection() throws SQLException {
        String jarPath = PathUtil.getPath(this.getClass()).getAbsolutePath();
        LOGGER.debug("default db path: {}/data", jarPath);
        String connectionString = getSetting(CONNECTION_STRING_ENV, String.format(DEFAULT_CONNECTION_STRING, jarPath));
        String user = getSetting(DB_USER, DEFAULT_DB_USER);
        String password = getSetting(DB_PASSWORD, DEFAULT_DB_PASSWORD);
        String dbDriver = getSetting(DB_DRIVER, null);

        // load the driver if necessary
        if (dbDriver != null) {
            final String driverPath = getSetting(DB_DRIVER_PATH, null);
            if (!driverPath.isEmpty()) {
                driver = load(dbDriver, driverPath);
            } else {
                driver = load(dbDriver);
            }
        }
        final Connection conn;
        if (connectionString.toLowerCase().contains("integrated security=true")
                || connectionString.toLowerCase().contains("trusted_connection=true")) {
            conn = DriverManager.getConnection(connectionString);
        } else {
            conn = DriverManager.getConnection(connectionString, user, password);
        }
        return conn;
    }

    /**
     * Loads the specified class using the system class loader and registers the driver with the driver manager.
     *
     * @param className the fully qualified name of the desired class
     * @return the loaded Driver
     * @throws SQLException thrown if the driver cannot be loaded
     */
    private Driver load(String className) throws SQLException {
        final ClassLoader loader = DBStore.class.getClassLoader();
        return load(className, loader);
    }

    /**
     * Loads the specified class by registering the supplied paths to the class loader and then registers the driver
     * with the driver manager. The pathToDriver argument is added to the class loader so that an external driver can be
     * loaded. Note, the pathToDriver can contain a semi-colon separated list of paths so any dependencies can be added
     * as needed. If a path in the pathToDriver argument is a directory all files in the directory are added to the
     * class path.
     *
     * @param className the fully qualified name of the desired class
     * @param pathToDriver the path to the JAR file containing the driver; note, this can be a semi-colon separated list
     * of paths
     * @return the loaded Driver
     * @throws SQLException thrown if the driver cannot be loaded
     */
    private Driver load(String className, String pathToDriver) throws SQLException {
        final ClassLoader parent = ClassLoader.getSystemClassLoader();
        final List<URL> urls = new ArrayList<>();
        final String[] paths = pathToDriver.split(File.pathSeparator);
        for (String path : paths) {
            final File file = new File(path);
            if (file.isDirectory()) {
                final File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        try {
                            urls.add(f.toURI().toURL());
                        } catch (MalformedURLException ex) {
                            LOGGER.debug("Unable to load database driver '{}'; invalid path provided '{}'", className,
                                    f.getAbsoluteFile(), ex);
                            throw new SQLException("Unable to load database driver. Invalid path provided", ex);
                        }
                    }
                }
            } else if (file.exists()) {
                try {
                    urls.add(file.toURI().toURL());
                } catch (MalformedURLException ex) {
                    LOGGER.debug("Unable to load database driver '{}'; invalid path provided '{}'", className,
                            file.getAbsoluteFile(), ex);
                    throw new SQLException("Unable to load database driver. Invalid path provided", ex);
                }
            }
        }
        final URLClassLoader loader = AccessController.doPrivileged(
                (PrivilegedAction<URLClassLoader>) () -> new URLClassLoader(urls.toArray(new URL[0]), parent));

        return load(className, loader);
    }

    /**
     * Loads the specified class using the supplied class loader and registers the driver with the driver manager.
     *
     * @param className the fully qualified name of the desired class
     * @param loader the class loader to use when loading the driver
     * @return the loaded Driver
     * @throws SQLException thrown if the driver cannot be loaded
     */
    private Driver load(String className, ClassLoader loader) throws SQLException {
        try {
            final Class<?> c = Class.forName(className, true, loader);
            // final Class c = loader.loadClass(className);
            final Driver driver = (Driver) c.getDeclaredConstructor().newInstance();

            // TODO add usage count so we don't de-register a driver that is in use.
            final Driver shim = new DriverShim(driver);
            // using the DriverShim to get around the fact that the DriverManager won't register a driver not in the
            // base class path
            DriverManager.registerDriver(shim);
            return shim;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
                | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            final String msg = String.format("Unable to load database driver '%s'", className);
            LOGGER.debug(msg, ex);
            throw new SQLException(msg, ex);
        }
    }

    /**
     * De-registers the driver.
     *
     * @param driver the driver to de-register
     */
    private void cleanup(Driver driver) {
        try {
            LOGGER.debug("Begin deregister driver");
            DriverManager.deregisterDriver(driver);
            LOGGER.debug("End deregister driver");
        } catch (SQLException ex) {
            LOGGER.debug("An error occurred unloading the database driver", ex);
        } catch (Throwable unexpected) {
            LOGGER.debug("An unexpected throwable occurred unloading the database driver", unexpected);
        }
    }

    @Override
    public void close() throws Exception {
        cleanup(driver);
    }
}
