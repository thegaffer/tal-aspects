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

package org.tpspencer.tal.util.aspects;

import org.tpspencer.tal.util.aspects.annotations.Trace;
import org.tpspencer.tal.util.aspects.annotations.TraceException;
import org.tpspencer.tal.util.aspects.annotations.RuntimeProfile;
import org.tpspencer.tal.util.aspects.loggers.SingletonAspectLogger;

/**
 * This aspect contains pointcuts for Traceable, Profilable
 * and Exception Traceable methods and delegates to the
 * appropriate logger.
 * 
 * @author Tom Spencer
 */
public aspect TraceAspect {
	
	pointcut traceClass(): within(@Trace *);
	pointcut traceMethod(): traceClass() && 
								execution(* *(..)) &&
								!execution(String toString()) &&
								!execution(* get*());
	
	pointcut traceExceptionClass(): within(@TraceException *);
	pointcut traceExceptionMethod(): traceExceptionClass() && 
										execution(* *(..)) &&
										!execution(String toString());
	
	pointcut profileClass(): within(@RuntimeProfile *);
	pointcut profileMethod(): profileClass() && 
								execution(* *(..)) &&
								!execution(String toString());
	
	before() : traceMethod() {
		SingletonAspectLogger.getInstance().getTraceLogger().traceBefore(thisJoinPoint);
	}
	
	Object around() : profileMethod() {
		long st = System.currentTimeMillis();
		Object ret = proceed();
		long t = System.currentTimeMillis() - st;
		SingletonAspectLogger.getInstance().getProfileLogger().traceProfile(thisJoinPoint, t);
		return ret;
	}
	
	after() returning(Object ret) : traceMethod() {
		SingletonAspectLogger.getInstance().getTraceLogger().traceAfter(thisJoinPoint, ret);
	}
	
	after() throwing(Exception e) : traceMethod() || traceExceptionMethod() {
		SingletonAspectLogger.getInstance().getExceptionLogger().traceException(thisJoinPoint, e);
	} 
}
