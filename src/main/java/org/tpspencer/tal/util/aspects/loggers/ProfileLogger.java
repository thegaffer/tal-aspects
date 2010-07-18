package org.tpspencer.tal.util.aspects.loggers;

import org.aspectj.lang.JoinPoint;

/**
 * This interface represents a class that will handle 
 * profiling of methods that are matched by the
 * {@link TraceAspect}.
 * 
 * @author Tom Spencer
 */
public interface ProfileLogger {

	/**
	 * Called by the aspect on a profiling method.
	 * 
	 * @param jp The join point
	 * @param timeTaken The time it has taken
	 */
	public abstract void traceProfile(JoinPoint jp, long timeTaken);
}
