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
