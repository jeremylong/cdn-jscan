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

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SearchableFileBufferTest {

    @Test
    void next() throws IOException {
        Path resource = Paths.get("src", "test", "resources", "sample.txt");
        try (SearchableFileBuffer buffer = new SearchableFileBuffer(resource, 5)) {
            assertTrue(buffer.hasNext());
            SearchableFileBuffer.Section section = buffer.next();
            assertEquals(0, section.getOffset());
            assertTrue("012345678901".equals(section.getCharBuffer().toString()));
        }
    }

    @Test
    void hasNext() throws IOException {
        Path resource = Paths.get("src", "test", "resources", "sample.txt");
        try (SearchableFileBuffer buffer = new SearchableFileBuffer(6, resource, 3)) {
            assertTrue(buffer.hasNext());
            SearchableFileBuffer.Section section = buffer.next();
            assertEquals(0, section.getOffset());
            String str = section.getCharBuffer().toString();
            assertTrue("012345".equals(str), "String obtained: " + str);
            assertTrue(buffer.hasNext());

            section = buffer.next();
            assertEquals(3, section.getOffset());
            str = section.getCharBuffer().toString();
            assertTrue("345678".equals(str), "String obtained: " + str);
            assertTrue(buffer.hasNext());

            section = buffer.next();
            assertEquals(6, section.getOffset());
            str = section.getCharBuffer().toString();
            assertTrue("678901".equals(str), "String obtained: " + str);
            assertFalse(buffer.hasNext());
        }
    }
}