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
package io.github.jeremylong.cdnjs.api;

/**
 * Exception thrown if there is a problem calling the APIs.
 *
 * @author Jeremy Long
 */
public class CdnjsApiException extends RuntimeException {
    /**
     * Generate a new exception.
     *
     * @param message the message
     */
    public CdnjsApiException(String message) {
        super(message);
    }

    /**
     * Generate a new exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public CdnjsApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Generate a new exception.
     *
     * @param cause the cause
     */
    public CdnjsApiException(Throwable cause) {
        super(cause);
    }
}
