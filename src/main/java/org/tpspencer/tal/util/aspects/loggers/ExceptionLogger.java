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
public interface ExceptionLogger {

	/**
	 * Called by the aspect when an exception is thrown.
	 * 
	 * @param jp The joinpoint
	 * @param e The exception
	 */
	public abstract void traceException(JoinPoint jp, Exception e);
}
