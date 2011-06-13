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

import javax.servlet.http.HttpServlet;

import org.talframework.tal.aspects.annotations.HttpTrace;
import org.talframework.tal.aspects.loggers.locator.SingletonAspectLogger;

/**
 * This aspect is intended to wrap any {@link HttpServlet}
 * request/response. It will invoke the Http logger to log
 * out the request parameters, optionally request attributes
 * and at the end the request attributes and the contents
 * of the session.
 *
 * @author Tom Spencer
 */
public aspect RequestAspect {
    
    pointcut requestMethod(): execution(@HttpTrace * *(..));
    
    before() : requestMethod() {
        SingletonAspectLogger.getInstance().getRequestLogger().traceBefore(thisJoinPoint);
    }
    
    after() : requestMethod() {
        SingletonAspectLogger.getInstance().getRequestLogger().traceAfter(thisJoinPoint, null);
    }
    
    after() throwing(Throwable t) : requestMethod() {
        SingletonAspectLogger.getInstance().getExceptionLogger().traceException(thisJoinPoint, t);
    }
}
