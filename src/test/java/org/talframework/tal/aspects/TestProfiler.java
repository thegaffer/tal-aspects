/*
 * Copyright 2010 Thomas Spencer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.talframework.tal.aspects;

import junit.framework.Assert;

import org.apache.commons.logging.LogFactory;
import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Action;
import org.jmock.api.Invocation;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Test;
import org.talframework.tal.aspects.loggers.profile.DefaultProfilingLogger;
import org.talframework.tal.aspects.support.ServiceInterface;
import org.talframework.tal.aspects.support.TestLogger;

/**
 * Tests that we can profile methods.
 *
 * @author Tom Spencer
 */
public class TestProfiler {
    
    private Mockery context = new JUnit4Mockery();

    @After
    public void after() {
        TestLogger logger = (TestLogger)LogFactory.getLog(DefaultProfilingLogger.class);
        logger.clear();
    }

    /**
     * Ensures profiling logs are output
     */
    @Test
    public void basic() {
        final ServiceInterface iface = context.mock(ServiceInterface.class);
        
        context.checking(new Expectations() {{
            oneOf(iface).profileTest("goodArg"); will(returnValue(true));
            oneOf(iface).profileTest("badArg"); will(new WaitAction());
        }});
        
        iface.profileTest("goodArg");
        iface.profileTest("badArg");
        
        TestLogger profilerLogger = (TestLogger)LogFactory.getLog(DefaultProfilingLogger.class);
        Assert.assertEquals(1, profilerLogger.getLogSize());
        Assert.assertEquals(1, profilerLogger.getLogSize("info"));
        Assert.assertTrue(profilerLogger.getLogs("info", false).get(0).contains("Profiling ["));
        Assert.assertTrue(profilerLogger.getLogs("info", false).get(0).contains("boolean org.talframework.tal.aspects.support.ServiceInterface.profileTest(String)"));
        Assert.assertTrue(profilerLogger.getLogs("info", false).get(0).contains("arg[0]=badArg"));
    }
    
    /**
     * Ensures no profiling logs even though waiting
     */
    @Test
    public void nonProfile() {
        final ServiceInterface iface = context.mock(ServiceInterface.class);
        
        context.checking(new Expectations() {{
            oneOf(iface).nonProfileTest(); will(new WaitAction());
        }});
        
        iface.nonProfileTest();
        
        TestLogger profilerLogger = (TestLogger)LogFactory.getLog(DefaultProfilingLogger.class);
        Assert.assertEquals(0, profilerLogger.getLogSize());
    }
    
    /**
     * Private class to wait before returning
     *
     * @author Tom Spencer
     */
    private static class WaitAction implements Action {
        public void describeTo(Description description) {
            description.appendText("waits 50ms before returning");
        }
        
        public Object invoke(Invocation invocation) throws Throwable {
            Thread.sleep(2050);
            return Boolean.TRUE;
        }
    }
}
