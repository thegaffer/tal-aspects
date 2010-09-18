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

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.Assert;

import org.apache.commons.logging.LogFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.talframework.tal.aspects.support.SimpleServlet;
import org.talframework.tal.aspects.support.SimpleLogger;

/**
 * This class tests that we can
 *
 * @author Tom Spencer
 */
public class TestRequestTracing {
    
    private Mockery context = new JUnit4Mockery();

    @Test
    public void basic() {
        final HttpServletRequest request = context.mock(HttpServletRequest.class);
        final HttpServletResponse response = context.mock(HttpServletResponse.class);
        final HttpSession session = context.mock(HttpSession.class);
        
        final Hashtable<String, String> params = new Hashtable<String, String>();
        params.put("test", "testing");
        params.put("anotherTest", "another");
        params.put("test", "test2");
        
        final Hashtable<String, String> attrs = new Hashtable<String, String>();
        
        final Hashtable<String, String> sess = new Hashtable<String, String>();
        
        final SimpleServlet servlet = new SimpleServlet();
        
        context.checking(new Expectations() {{
            // Before logging
            oneOf(request).getParameterNames(); will(returnValue(params.keys()));
            oneOf(request).getParameter("test"); will(returnValue("testing"));
            oneOf(request).getParameter("anotherTest"); will(returnValue("another"));
            //oneOf(request).getParameter("test"); will(returnValue("test2"));
            oneOf(request).getCookies(); will(returnValue(null));
            oneOf(request).getAttributeNames(); will(returnValue(attrs.keys()));
            
            // Simple Servlet
            oneOf(request).getParameter("test"); will(returnValue("testing"));
            oneOf(request).setAttribute("requestTest", "testing");
            oneOf(request).getSession(); will(returnValue(session));
            oneOf(session).setAttribute("sessionTest", "testing");
            
            // After logging
            oneOf(request).getAttributeNames(); will(returnValue(attrs.keys()));
            oneOf(request).getSession(false); will(returnValue(session));
            oneOf(session).getAttributeNames(); will(returnValue(sess.keys()));
        }});
        
        servlet.doGet(request, response);
        
        SimpleLogger logger = (SimpleLogger)LogFactory.getLog(SimpleServlet.class);
        Assert.assertEquals(4, logger.getLogSize());
        Assert.assertEquals(2, logger.getLogSize("trace"));
        Assert.assertEquals(2, logger.getLogSize("debug"));
        context.assertIsSatisfied();
    }
}
