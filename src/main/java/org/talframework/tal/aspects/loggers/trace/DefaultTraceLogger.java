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
package org.talframework.tal.aspects.loggers.trace;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.talframework.tal.aspects.loggers.LoggerHelper;
import org.talframework.tal.aspects.loggers.TraceLogger;
import org.talframework.tal.aspects.loggers.locator.SingletonAspectLogger;

/**
 * This class is the default and in-built trace logger. It will
 * be fine for most uses, but can be overridden through the
 * {@link SingletonAspectLogger} if needed for performance or
 * other runtime environment reasons.
 * 
 * @author Tom Spencer
 */
public final class DefaultTraceLogger implements TraceLogger {
	private static final Log logger = LogFactory.getLog(DefaultTraceLogger.class);
	
	/**
	 * Logs out a message to state the method has started with
	 * the arguments that is has (if any). The trace logger as
	 * a whole must be set at debug level and the actual classes
	 * logger must be a trace to see anything.
	 */
	public void traceBefore(JoinPoint jp) {
		if( !logger.isDebugEnabled() ) return;
		
		Log traceLogger = LoggerHelper.getLogger(jp.getStaticPart().getSignature().getDeclaringType());
		if( traceLogger.isTraceEnabled() ) {
			StringBuilder builder = new StringBuilder();
			
			builder.append(">>> Starting: ").append(jp.getStaticPart().getSignature().getName());
			LoggerHelper.appendArguments(jp.getArgs(), builder);
			
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
		
		Log traceLogger = LoggerHelper.getLogger(jp.getStaticPart().getSignature().getDeclaringType());
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
