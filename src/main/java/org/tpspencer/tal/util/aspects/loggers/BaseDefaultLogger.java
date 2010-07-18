package org.tpspencer.tal.util.aspects.loggers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class BaseDefaultLogger {
	
	/**
	 * Helper to get the logger for the class we are running.
	 * This is extracted because it may be slow and require
	 * some additional work.
	 * 
	 * @param cls The class to get logger for
	 * @return The logger
	 */
	protected Log getTraceLogger(Class<?> cls) {
		return LogFactory.getLog(cls);
	}
}
