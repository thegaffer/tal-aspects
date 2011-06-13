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
package org.talframework.tal.aspects.loggers.http;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.talframework.tal.aspects.loggers.LoggerHelper;
import org.talframework.tal.aspects.loggers.TraceLogger;

/**
 * This class finds the HttpServletRequest from the arguments
 * and outputs the parameters, attributes and session as
 * indicated by the aspect on the method. The
 * {@link DefaultRequestLogger} log level should be set to
 * DEBUG for this to output, but actual output uses the logger
 * of the target class.
 *
 * @author Tom Spencer
 */
public final class DefaultRequestLogger implements TraceLogger {
    private static final Log logger = LogFactory.getLog(DefaultRequestLogger.class);
    
    /**
     * Logs out the request parameters and optionally the
     * request attributes for the first {@link HttpServletRequest}
     * argument to method.
     */
    public void traceBefore(JoinPoint jp) {
        if( !logger.isDebugEnabled() ) return;
        
        Log logger = LoggerHelper.getLogger(jp.getStaticPart().getSignature().getDeclaringType());
        if( logger.isDebugEnabled() ) {
            HttpServletRequest req = findRequest(jp);
            if( req == null ) return;
        
            if( logger.isDebugEnabled() ) logRequestParameters(req, logger);
            if( logger.isTraceEnabled() ) logRequestAttributes(req, logger);
        }
    }

    /**
     * Optionally logs out the ending request attributes and
     * session for the first {@link HttpServletRequest} argument
     * to the method. 
     */
    public void traceAfter(JoinPoint jp, Object retVal) {
        if( !logger.isDebugEnabled() ) return;
        
        Log logger = LoggerHelper.getLogger(jp.getStaticPart().getSignature().getDeclaringType());
        if( logger.isDebugEnabled() ) {
            HttpServletRequest req = findRequest(jp);
            if( req == null ) return;
            
            if( logger.isTraceEnabled() ) logRequestAttributes(req, logger);
            if( logger.isDebugEnabled() ) logSessionAttributes(req, logger);
        }
    }
    
    /**
     * Helper to find the first {@link HttpServletRequest} in the
     * method being called.
     * 
     * @param jp The joinpoint
     * @return The first {@link HttpServletRequest} arg.
     */
    private HttpServletRequest findRequest(JoinPoint jp) {
        HttpServletRequest ret = null;
        
        Object[] args = jp.getArgs();
        if( args != null ) {
            for( int i = 0 ; i < args.length ; i++ ) {
                if( args[i] instanceof HttpServletRequest ) {
                    ret = (HttpServletRequest)args[i];
                    break;
                }
            }
        }
        
        return ret;
    }
    
    /**
     * Helper to log out the request parameters and cookies
     * at debug level.
     * 
     * @param req The request
     * @param logger The logger
     */
    @SuppressWarnings("unchecked")
    private void logRequestParameters(HttpServletRequest req, Log logger) {
        StringBuilder buf = new StringBuilder();
        buf.append("Request Parameters: ");
        buf.append("\n\t**** Request Parameters ****");
        Enumeration e = req.getParameterNames();
        while( e.hasMoreElements() ) {
            String k = (String)e.nextElement();
            buf.append("\n\t").append(k).append("=").append(req.getParameter(k));
        }
        
        Cookie[] cookies = req.getCookies();
        if( cookies != null && cookies.length > 0 ) {
            for( int i = 0 ; i < cookies.length ; i++ ) {
                buf.append("\n\tcookie[").append(i).append("] ");
                if( cookies[i].getPath() != null ) buf.append(cookies[i].getPath()).append('/');
                buf.append(cookies[i].getName()).append('=');
                buf.append(cookies[i].getValue());
            }
        }
        
        buf.append("\n\t**** End Request Parameters ****");
        logger.debug(buf.toString());
    }
    
    /**
     * Helper to log out the request attributes at trace level
     * 
     * @param req The request
     * @param logger The logger
     */
    @SuppressWarnings("unchecked")
    private void logRequestAttributes(HttpServletRequest req, Log logger) {
        StringBuilder buf = new StringBuilder();
        buf.append("Request Attributes: ");
        buf.append("\n\t**** Request Attributes ****");
        Enumeration e = req.getAttributeNames();
        while( e.hasMoreElements() ) {
            String k = (String)e.nextElement();
            buf.append("\n\t").append(k).append("=").append(LoggerHelper.getValue(req.getAttribute(k)));
        }
        buf.append("\n\t**** End Request Attrobites ****");
        logger.trace(buf.toString());
    }
    
    /**
     * Helper to log out the session attributes at debug level
     * 
     * @param req The request
     * @param logger The logger
     */
    @SuppressWarnings("unchecked")
    private void logSessionAttributes(HttpServletRequest req, Log logger) {
        HttpSession session = req.getSession(false);
        if( session == null ) return;
        
        StringBuilder buf = new StringBuilder();
        buf.append("Session Contents: ");
        buf.append("\n\t**** Session Contents ****");
        Enumeration e = session.getAttributeNames();
        while( e.hasMoreElements() ) {
            String k = (String)e.nextElement();
            buf.append("\n\t").append(k).append("=").append(LoggerHelper.getValue(session.getAttribute(k)));
        }
        buf.append("\n\t**** End Session Contents ****");
        logger.debug(buf.toString());
    }
}
