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

package org.tpspencer.tal.util.aspects.loggers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

/**
 * This class is the default and in-built trace logger. It will
 * be fine for most uses, but can be overridden through the
 * {@link SingletonAspectLogger} if needed for performance or
 * other runtime environment reasons.
 * 
 * @author Tom Spencer
 */
public final class DefaultTraceLogger extends BaseDefaultLogger implements TraceLogger {
	private static final Log logger = LogFactory.getLog(DefaultTraceLogger.class);
	
	/**
	 * Logs out a message to state the method has started with
	 * the arguments that is has (if any). The trace logger as
	 * a whole must be set at debug level and the actual classes
	 * logger must be a trace to see anything.
	 */
	public void traceBefore(JoinPoint jp) {
		if( !logger.isDebugEnabled() ) return;
		
		Object target = jp.getThis();
		Log traceLogger = getTraceLogger(target.getClass());
		if( traceLogger.isTraceEnabled() ) {
			StringBuilder builder = new StringBuilder();
			
			builder.append(">>> Starting: ").append(jp.getStaticPart().getSignature().getName());
			Object[] args = jp.getArgs();
			if( args != null && args.length > 0 ) {
				for( int i = 0 ; i < args.length ; i++ ) {
					builder.append("\n\targ[").append(i).append("]=").append(args[i]);
				}
			}
			
			traceLogger.trace(builder.toString());
		}
	}
	
	/**
	 * Logs out a messages to state the method has ended outputting
	 * the return value. The trace logger as a whole must be at 
	 * debug level and the actual classes logger must be at trace to
	 * see anything.
	 */
	public void traceAfter(JoinPoint jp, Object retVal) {
		if( !logger.isDebugEnabled() ) return;
		
		Object target = jp.getThis();
		Log traceLogger = getTraceLogger(target.getClass());
		if( traceLogger.isTraceEnabled() ) {
			StringBuilder builder = new StringBuilder();
			
			builder.append("<<< Ending: ").append(jp.getStaticPart().getSignature().getName());
			if( retVal != null ) {
				builder.append("\n\tretVal=").append(retVal);
			}
			
			traceLogger.trace(builder.toString());
		}
	}
}
