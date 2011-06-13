/**
 * Copyright (C) 2011 Tom Spencer <thegaffer@tpspencer.com>
 *
 * This file is part of TAL.
 *
 * TAL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TAL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TAL. If not, see <http://www.gnu.org/licenses/>.
 *
 * Note on dates: Year above is the year this code was built. This
 * project first created in 2009. Code was created between these two
 * years inclusive.
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
import org.talframework.tal.aspects.support.SimpleLogger;

/**
 * Tests that we can profile methods.
 *
 * @author Tom Spencer
 */
public class TestProfiler {
    
    private Mockery context = new JUnit4Mockery();

    @After
    public void after() {
        SimpleLogger logger = (SimpleLogger)LogFactory.getLog(DefaultProfilingLogger.class);
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
        
        SimpleLogger profilerLogger = (SimpleLogger)LogFactory.getLog(DefaultProfilingLogger.class);
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
        
        SimpleLogger profilerLogger = (SimpleLogger)LogFactory.getLog(DefaultProfilingLogger.class);
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
