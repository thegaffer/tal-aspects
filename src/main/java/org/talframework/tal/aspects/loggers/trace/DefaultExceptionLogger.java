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
		Log traceLogger = LoggerHelper.getLogger(jp.getStaticPart().getSignature().getDeclaringType());
		StringBuilder builder = new StringBuilder();
		
		builder.append("!!! Error From: ").append(jp.getStaticPart().getSignature().getName());
		builder.append("\n\texception=").append(t);
		LoggerHelper.appendArguments(jp.getArgs(), builder);
		
		traceLogger.error(builder.toString());
		if( traceLogger.isDebugEnabled() ) t.printStackTrace();
	}
}
