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
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class SearchableFileBuffer implements AutoCloseable, Iterator<SearchableFileBuffer.Section> {

    private final long channelSize;
    private final int searchTermLength;
    private final int bufferSize;
    private long offset;
    private final Path path;
    private FileChannel channel;

    public SearchableFileBuffer(Path path) throws IOException {
        this(16384, path, 0);
    }

    public SearchableFileBuffer(Path path, int searchTermLength) throws IOException {
        this(16384, path, searchTermLength);
    }

    public SearchableFileBuffer(int buffersize, Path path, int searchTermLength) throws IOException {
        this.offset = 0;
        this.bufferSize = buffersize;
        this.searchTermLength = searchTermLength;
        this.path = path;
        this.channel = FileChannel.open(path, StandardOpenOption.READ);
        this.channelSize = channel.size();
    }

    @Override
    public void close() throws IOException {
        if (channel != null) {
            channel.close();
            channel = null;
        }
    }

    @Override
    public boolean hasNext() {
        return channel != null && offset < channelSize;
    }

    @Override
    public Section next() {
        CharBuffer charBuffer = null;
        long stepSize = bufferSize;
        boolean atEnd = false;
        if ((offset + bufferSize) >= channelSize) {
            stepSize = channelSize - offset;
            atEnd = true;
        }
        MappedByteBuffer byteBuffer;
        IOException exception = null;
        try {
            byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, offset, stepSize);
            if (byteBuffer != null) {
                // TODO make charaset a configurable optiom
                charBuffer = StandardCharsets.UTF_8.decode(byteBuffer);
            }
        } catch (IOException ex) {
            exception = ex;
        }
        Section section = new Section(offset, charBuffer, path, exception);
        offset += stepSize;
        if (!atEnd) {
            offset -= searchTermLength;
        }
        return section;
    }

    public static class Section {
        private final long offset;
        private final Path path;
        private final CharBuffer charBuffer;
        private final IOException exception;

        @SuppressFBWarnings(value = {"EI_EXPOSE_REP2"})
        public Section(long offset, CharBuffer charBuffer, Path path, IOException exception) {
            this.offset = offset;
            this.charBuffer = charBuffer;
            this.path = path;
            this.exception = exception;
        }

        public long getOffset() {
            return offset;
        }

        public Path getPath() {
            return path;
        }

        @SuppressFBWarnings(value = {"EI_EXPOSE_REP"})
        public CharBuffer getCharBuffer() throws SearchableFileException {
            if (exception != null) {
                throw new SearchableFileException("Error reading " + path.toString() + " at offset " + offset,
                        exception);
            }
            return charBuffer;
        }
    }

    public static class SearchableFileException extends IOException {

        public SearchableFileException() {
            super();
        }

        public SearchableFileException(String message) {
            super(message);
        }

        public SearchableFileException(String message, Throwable cause) {
            super(message, cause);
        }

        public SearchableFileException(Throwable cause) {
            super(cause);
        }
    }
}
