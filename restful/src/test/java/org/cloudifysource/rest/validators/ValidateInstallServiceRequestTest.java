/*******************************************************************************
 * Copyright (c) 2013 GigaSpaces Technologies Ltd. All rights reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *******************************************************************************/
package org.cloudifysource.rest.validators;

import java.io.IOException;

import org.cloudifysource.dsl.internal.CloudifyErrorMessages;
import org.junit.Test;

public class ValidateInstallServiceRequestTest extends InstallServiceValidatorTest {

    private static final String WRONG_DEBUG_MODE = "no_such_mode";
    private static final String DEBUG_EVENTS = "init,install";
    private static final String DUPLICATE_DEBUG_EVENTS = DEBUG_EVENTS + ",init";
    private static final String WRONG_DEBUG_EVENTS = DEBUG_EVENTS + ", EVENT_NOT_EXIST";

    @Override
    public void init() {
    }

	@Test
	public void testWrongDebugEventsAndAll() throws IOException {
		setDebugAll(true);
		setDebugEvents(DEBUG_EVENTS);
		setExceptionCause(CloudifyErrorMessages.DEBUG_EVENTS_AND_ALL_SET.getName());
		testValidator();
	}

    @Test
    public void testWrongDebugEvents() throws IOException {
        setDebugAll(false);
        setDebugEvents(WRONG_DEBUG_EVENTS);
        setExceptionCause(CloudifyErrorMessages.DEBUG_EVENT_UNKNOWN.getName());
        testValidator();
    }

    @Test
    public void testDuplicateDebugEvents() throws IOException {
        setDebugAll(false);
        setDebugEvents(DUPLICATE_DEBUG_EVENTS);
        setExceptionCause(CloudifyErrorMessages.DEBUG_EVENT_REPEATS.getName());
        testValidator();
    }

    @Test
    public void testWrongDebugMode() throws IOException {
        setDebugAll(true);
        setDebugMode(WRONG_DEBUG_MODE);
        setExceptionCause(CloudifyErrorMessages.DEBUG_UNKNOWN_MODE.getName());
        testValidator();
    }

    @Override
    public InstallServiceValidator getValidatorInstance() {
        return new ValidateInstallServiceRequest();
    }


}
