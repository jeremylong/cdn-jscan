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
package io.github.jeremylong.cdnjs.scanner;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Scanner implements Iterator<Path> {

    private static final int SEARCH_DEPTH = 64;
    private final String includePattern;
    private final String excludePattern;
    private final List<Path> paths = new ArrayList<>();
    private Iterator<Path> iterator;

    public Scanner() {
        includePattern = "**/*.*";
        excludePattern = null;
    }

    public Scanner(Path path, String includePattern, String excludePattern) throws IOException {
        this.includePattern = includePattern;
        this.excludePattern = excludePattern;
        if (path.toFile().isFile()) {
            paths.add(path);
        } else {
            paths.addAll(scan(path));
        }
    }

    // https://stackoverflow.com/a/39903784/1995422
    static boolean isTextFile(Path path) {
        String type = null;
        try {
            type = Files.probeContentType(path);
        } catch (IOException e) {
            return false;
        }
        if (type == null) {
            // type couldn't be determined, assume binary
            return false;
        } else if (type.startsWith("text")) {
            return true;
        } else {
            // type isn't text
            return false;
        }
    }

    public void add(Path path) throws IOException {
        if (path.toFile().isFile()) {
            paths.add(path);
        } else {
            paths.addAll(scan(path));
        }
    }

    public Collection<Path> scan(Path dir) throws IOException {
        final PathMatcher include = FileSystems.getDefault().getPathMatcher("glob:" + includePattern);
        final PathMatcher exclude;
        if (excludePattern != null) {
            exclude = FileSystems.getDefault().getPathMatcher("glob:" + excludePattern);
        } else {
            exclude = null;
        }
        return Files
                .find(dir, SEARCH_DEPTH,
                        (path, attr) -> include.matches(path) && (exclude == null || !exclude.matches(path)))
                .parallel().filter(Scanner::isTextFile).collect(Collectors.toSet());
    }

    @Override
    public boolean hasNext() {
        if (iterator == null) {
            iterator = paths.iterator();
        }
        return iterator.hasNext();
    }

    @Override
    public Path next() {
        if (iterator == null) {
            iterator = paths.iterator();
        }
        return iterator.next();
    }
}
