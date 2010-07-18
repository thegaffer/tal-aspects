package org.tpspencer.tal.util.aspects.loggers;

import org.aspectj.lang.JoinPoint;

/**
 * This interface represents a class that will handle
 * the logging of Exceptions that have occurred in
 * either methods that are being traced fully or just
 * for exceptions
 * 
 * @author Tom Spencer
 */
public interface TraceLogger {

	/**
	 * Called by the aspect to trace the start of methods
	 * 
	 * @param jp The join point
	 */
	public abstract void traceBefore(JoinPoint jp);
	
	/**
	 * Called by the aspect to trace the end of methods
	 * 
	 * @param jp The join point
	 * @param retVal The return value
	 */
	public abstract void traceAfter(JoinPoint jp, Object retVal);
}
