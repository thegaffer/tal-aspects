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

package org.talframework.tal.aspects.loggers.trace;

import org.apache.commons.logging.Log;
import org.aspectj.lang.JoinPoint;
import org.talframework.tal.aspects.loggers.ExceptionLogger;
import org.talframework.tal.aspects.loggers.LoggerHelper;

/**
 * This class actually performs the exception logging when
 * an error has occured.
 * 
 * @author Tom Spencer
 */
public final class DefaultExceptionLogger implements ExceptionLogger {
	
	/**
	 * Logs out that the method has failed listing out the full
	 * input parameters and the exception. Logging is done as
	 * error level
	 */
	public void traceException(JoinPoint jp, Throwable t) {
		Object target = jp.getThis();
		Log traceLogger = LoggerHelper.getLogger(target.getClass());
		StringBuilder builder = new StringBuilder();
		
		builder.append("!!! Error From: ").append(jp.getStaticPart().getSignature().getName());
		builder.append("\n\texception=").append(t);
		Object[] args = jp.getArgs();
		if( args != null && args.length > 0 ) {
			for( int i = 0 ; i < args.length ; i++ ) {
				builder.append("\n\targ[").append(i).append("]=").append(args[i]);
			}
		}
		
		traceLogger.error(builder.toString());
		if( traceLogger.isDebugEnabled() ) t.printStackTrace();
	}
}
