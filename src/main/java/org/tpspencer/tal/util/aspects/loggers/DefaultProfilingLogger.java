package org.tpspencer.tal.util.aspects.loggers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

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
