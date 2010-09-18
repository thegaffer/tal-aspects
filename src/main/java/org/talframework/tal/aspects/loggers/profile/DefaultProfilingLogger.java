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

package org.talframework.tal.aspects.loggers.profile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
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
	private long threshold = 2000;
	
	public DefaultProfilingLogger() {
	}
	
	public DefaultProfilingLogger(long threshold) {
		this.threshold = threshold;
	}
	
	public void traceProfile(JoinPoint jp, long timeTaken) {
		if( logger.isInfoEnabled() && timeTaken > threshold ) {
			StringBuilder builder = new StringBuilder();
			
			builder.append("*** Profiling [").append(timeTaken).append("ms]: ");
			builder.append(jp.getStaticPart().getSignature());
			Object[] args = jp.getArgs();
			if( args != null && args.length > 0 ) {
				for( int i = 0 ; i < args.length ; i++ ) {
					builder.append("\n\targ[").append(i).append("]=").append(args[i]);
				}
			}
			
			logger.info(builder.toString());
		}
	}
}
