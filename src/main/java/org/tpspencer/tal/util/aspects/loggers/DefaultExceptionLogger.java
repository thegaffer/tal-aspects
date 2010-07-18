package org.tpspencer.tal.util.aspects.loggers;

import org.apache.commons.logging.Log;
import org.aspectj.lang.JoinPoint;

/**
 * This class actually performs the exception logging when
 * an error has occured.
 * 
 * @author Tom Spencer
 */
public final class DefaultExceptionLogger extends BaseDefaultLogger implements ExceptionLogger {
	
	/**
	 * Logs out that the method has failed listing out the full
	 * input parameters and the exception. Logging is done as
	 * error level
	 */
	public void traceException(JoinPoint jp, Exception e) {
		Object target = jp.getThis();
		Log traceLogger = getTraceLogger(target.getClass());
		StringBuilder builder = new StringBuilder();
		
		builder.append("!!! Error From: ").append(jp.getStaticPart().getSignature().getName());
		builder.append("\n\texception=").append(e);
		Object[] args = jp.getArgs();
		if( args != null && args.length > 0 ) {
			for( int i = 0 ; i < args.length ; i++ ) {
				builder.append("\n\targ[").append(i).append("]=").append(args[i]);
			}
		}
		
		traceLogger.error(builder.toString());
		if( traceLogger.isDebugEnabled() ) e.printStackTrace();
	}
}
