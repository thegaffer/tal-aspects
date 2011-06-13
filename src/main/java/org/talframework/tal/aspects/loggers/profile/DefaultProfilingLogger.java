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
package org.talframework.tal.aspects.loggers.profile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.talframework.tal.aspects.loggers.LoggerHelper;
import org.talframework.tal.aspects.loggers.ProfileLogger;

/**
 * This class actually performs the exception logging when
 * an error has occured.
 * 
 * @author Tom Spencer
 */
public final class DefaultProfilingLogger implements ProfileLogger {
	private static final Log logger = LogFactory.getLog(DefaultProfilingLogger.class);
	
	/** The threshold above which we log out, default is 2 seconds */
	private final long threshold;
	
	public DefaultProfilingLogger() {
	    this.threshold = 2000;
	}
	
	public DefaultProfilingLogger(long threshold) {
		this.threshold = threshold;
	}
	
	public void traceProfile(JoinPoint jp, long timeTaken) {
		if( logger.isInfoEnabled() && timeTaken > threshold ) {
			StringBuilder builder = new StringBuilder();
			
			builder.append("*** Profiling [").append(timeTaken).append("ms]: ");
			builder.append(jp.getStaticPart().getSignature());
			LoggerHelper.appendArguments(jp.getArgs(), builder);
			
			logger.info(builder.toString());
		}
	}
}
