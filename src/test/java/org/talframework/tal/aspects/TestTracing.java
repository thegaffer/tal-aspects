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
import org.junit.After;
import org.junit.Test;
import org.talframework.tal.aspects.support.SimpleTraceClass;
import org.talframework.tal.aspects.support.TestLogger;

/**
 * This class tests that we trace methods correctly.
 *
 * @author Tom Spencer
 */
public class TestTracing {
    
    private SimpleTraceClass underTest = new SimpleTraceClass();
    
    @After
    public void after() {
        TestLogger logger = (TestLogger)LogFactory.getLog(SimpleTraceClass.class);
        logger.clear();
    }

    @Test
    public void basic() {
        underTest.basic("aString", 1234543);
        
        TestLogger logger = (TestLogger)LogFactory.getLog(SimpleTraceClass.class);
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
        
        TestLogger logger = (TestLogger)LogFactory.getLog(SimpleTraceClass.class);
        Assert.assertEquals(2, logger.getLogSize());
        Assert.assertEquals(1, logger.getLogSize("trace"));
        Assert.assertEquals(1, logger.getLogSize("error"));
    }
    
    @Test
    public void noArgs() {
        underTest.noArgs();
        
        TestLogger logger = (TestLogger)LogFactory.getLog(SimpleTraceClass.class);
        Assert.assertEquals(2, logger.getLogSize());
        Assert.assertEquals(2, logger.getLogSize("trace"));
    }
    
    @Test
    public void noReturn() {
        underTest.noReturn(444.5678);
        
        TestLogger logger = (TestLogger)LogFactory.getLog(SimpleTraceClass.class);
        Assert.assertEquals(2, logger.getLogSize());
        Assert.assertEquals(2, logger.getLogSize("trace"));
    }
    
    @Test
    public void noTrace() {
        underTest.noTrace("noTrace", 1234543);
        
        TestLogger logger = (TestLogger)LogFactory.getLog(SimpleTraceClass.class);
        Assert.assertEquals(0, logger.getLogSize());
    }
}
