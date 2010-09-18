package org.talframework.tal.aspects.loggers;

import org.aspectj.lang.JoinPoint;

/**
 * This interface represents something that logs out when
 * a class is created.
 *
 * @author Tom Spencer
 */
public interface ConstructionLogger {

    /**
     * Called when the constructor is called
     * 
     * @param jp The join point
     */
    public abstract void traceCreation(JoinPoint jp);
}
