/*
 * Copyright 2009 Thomas Spencer
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

import org.talframework.tal.aspects.annotations.Trace;
import org.talframework.tal.aspects.loggers.locator.SingletonAspectLogger;

/**
 * This aspect contains pointcuts for Traceable, Profilable
 * and Exception Traceable methods and delegates to the
 * appropriate logger.
 * 
 * @author Tom Spencer
 */
public aspect TraceAspect {
	
	pointcut traceMethod(): execution(@Trace * *(..));
	
	before() : traceMethod() {
		SingletonAspectLogger.getInstance().getTraceLogger().traceBefore(thisJoinPoint);
	}
	
	after() returning(Object ret) : traceMethod() {
        SingletonAspectLogger.getInstance().getTraceLogger().traceAfter(thisJoinPoint, ret);
    }
	
	after() throwing(Throwable t) : traceMethod() {
        SingletonAspectLogger.getInstance().getExceptionLogger().traceException(thisJoinPoint, t);
    }
}