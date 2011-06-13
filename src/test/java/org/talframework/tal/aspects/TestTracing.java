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
import org.junit.After;
import org.junit.Test;
import org.talframework.tal.aspects.support.SimpleTraceClass;
import org.talframework.tal.aspects.support.SimpleLogger;

/**
 * This class tests that we trace methods correctly.
 *
 * @author Tom Spencer
 */
public class TestTracing {
    
    private SimpleTraceClass underTest = new SimpleTraceClass();
    
    @After
    public void after() {
        SimpleLogger logger = (SimpleLogger)LogFactory.getLog(SimpleTraceClass.class);
        logger.clear();
    }

    @Test
    public void basic() {
        underTest.basic("aString", 1234543);
        
        SimpleLogger logger = (SimpleLogger)LogFactory.getLog(SimpleTraceClass.class);
        Assert.assertEquals(2, logger.getLogSize());
        Assert.assertEquals(2, logger.getLogSize("trace"));
    }
    
    @Test
    public void fails() {
        try {
            underTest.fails("onFailure", 987654);
            Assert.assertTrue(false);
        }
        catch( IllegalArgumentException e ) {}
        
        SimpleLogger logger = (SimpleLogger)LogFactory.getLog(SimpleTraceClass.class);
        Assert.assertEquals(2, logger.getLogSize());
        Assert.assertEquals(1, logger.getLogSize("trace"));
        Assert.assertEquals(1, logger.getLogSize("error"));
    }
    
    @Test
    public void noArgs() {
        underTest.noArgs();
        
        SimpleLogger logger = (SimpleLogger)LogFactory.getLog(SimpleTraceClass.class);
        Assert.assertEquals(2, logger.getLogSize());
        Assert.assertEquals(2, logger.getLogSize("trace"));
    }
    
    @Test
    public void noReturn() {
        underTest.noReturn(444.5678);
        
        SimpleLogger logger = (SimpleLogger)LogFactory.getLog(SimpleTraceClass.class);
        Assert.assertEquals(2, logger.getLogSize());
        Assert.assertEquals(2, logger.getLogSize("trace"));
    }
    
    @Test
    public void noTrace() {
        underTest.noTrace("noTrace", 1234543);
        
        SimpleLogger logger = (SimpleLogger)LogFactory.getLog(SimpleTraceClass.class);
        Assert.assertEquals(0, logger.getLogSize());
    }
    
    @Test
    public void warning() {
        underTest.shouldntBeCalled("aString");
        
        SimpleLogger logger = (SimpleLogger)LogFactory.getLog(SimpleTraceClass.class);
        Assert.assertEquals(1, logger.getLogSize());
        Assert.assertEquals(1, logger.getLogSize("warn"));
    }
}
