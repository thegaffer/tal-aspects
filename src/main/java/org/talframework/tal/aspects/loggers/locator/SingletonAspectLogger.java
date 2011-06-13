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
package org.talframework.tal.aspects.loggers.locator;

import org.talframework.tal.aspects.loggers.ConstructionLogger;
import org.talframework.tal.aspects.loggers.ExceptionLogger;
import org.talframework.tal.aspects.loggers.ProfileLogger;
import org.talframework.tal.aspects.loggers.TraceLogger;
import org.talframework.tal.aspects.loggers.http.DefaultRequestLogger;
import org.talframework.tal.aspects.loggers.profile.DefaultProfilingLogger;
import org.talframework.tal.aspects.loggers.trace.DefaultExceptionLogger;
import org.talframework.tal.aspects.loggers.trace.DefaultPrototypeLogger;
import org.talframework.tal.aspects.loggers.trace.DefaultTraceLogger;
import org.talframework.tal.aspects.loggers.trace.DefaultWarnLogger;


/**
 * The singleton trace logger exists to serve up the various
 * tracing loggers to the aspect. By using this class we are
 * able to various how we trace normal start/entry, exceptions
 * and runtime performance. Defaults are provided though.
 * 
 * <p>To override any loggers, get the {@link SingletonAspectLogger} 
 * via its getInstance static method and then inject in your own
 * loggers via the setters.</p>
 * 
 * @author Tom Spencer
 */
public final class SingletonAspectLogger {
	/** The single instance of this class */
	private static final SingletonAspectLogger INSTANCE = new SingletonAspectLogger();
	
	/** Holds the trace logger */
	private TraceLogger traceLogger = new DefaultTraceLogger();
	/** Holds the warning logger */
    private TraceLogger warningLogger = new DefaultWarnLogger();
	/** Holds the exception logger */
	private ExceptionLogger exceptionLogger = new DefaultExceptionLogger();
	/** Holds the profile logger */
	private ProfileLogger profileLogger = new DefaultProfilingLogger();
	/** Holds the http request logger */
	private TraceLogger requestLogger = new DefaultRequestLogger();
	/** Holds the prototype logger */
	private ConstructionLogger prototypeLogger = new DefaultPrototypeLogger();
	
	/**
	 * Hidden constructor to ensure no more instances are created
	 */
	private SingletonAspectLogger() {
	}
	
	/**
	 * @return The single instance of {@link SingletonAspectLogger} (per classloader)
	 */
	public static SingletonAspectLogger getInstance() {
		return INSTANCE;
	}
	
	/**
	 * @return The trace logger in use
	 */
	public TraceLogger getTraceLogger() {
		return this.traceLogger;
	}
	
	/**
	 * Call to set the trace logger
	 * 
	 * @param logger The trace logger to use
	 */
	public void setTraceLogger(TraceLogger logger) {
		if( logger == null ) throw new IllegalArgumentException("You cannot provide a null trace logger");
		this.traceLogger = logger;
	}

	/**
	 * @return The exception logger to use
	 */
	public ExceptionLogger getExceptionLogger() {
		return exceptionLogger;
	}

	/**
	 * @param exceptionLogger The new exception logger to set (cannot be null)
	 */
	public void setExceptionLogger(ExceptionLogger exceptionLogger) {
		this.exceptionLogger = exceptionLogger;
	}

	/**
	 * @return The profiling logger
	 */
	public ProfileLogger getProfileLogger() {
		return profileLogger;
	}

	/**
	 * @param profileLogger The profile logger to set (cannot be null)
	 */
	public void setProfileLogger(ProfileLogger profileLogger) {
		this.profileLogger = profileLogger;
	}

    /**
     * @return the requestLogger
     */
    public TraceLogger getRequestLogger() {
        return requestLogger;
    }

    /**
     * Setter for the requestLogger field
     *
     * @param requestLogger the requestLogger to set
     */
    public void setRequestLogger(TraceLogger requestLogger) {
        this.requestLogger = requestLogger;
    }

    /**
     * @return the prototypeLogger
     */
    public ConstructionLogger getPrototypeLogger() {
        return prototypeLogger;
    }

    /**
     * Setter for the prototypeLogger field
     *
     * @param prototypeLogger the prototypeLogger to set
     */
    public void setPrototypeLogger(ConstructionLogger prototypeLogger) {
        this.prototypeLogger = prototypeLogger;
    }

    /**
     * @return the warningLogger
     */
    public TraceLogger getWarningLogger() {
        return warningLogger;
    }

    /**
     * Setter for the warningLogger field
     *
     * @param warningLogger the warningLogger to set
     */
    public void setWarningLogger(TraceLogger warningLogger) {
        this.warningLogger = warningLogger;
    }
    
}
