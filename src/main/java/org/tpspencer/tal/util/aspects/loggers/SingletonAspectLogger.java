package org.tpspencer.tal.util.aspects.loggers;


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
public class SingletonAspectLogger {
	/** The single instance of this class */
	private static final SingletonAspectLogger INSTANCE = new SingletonAspectLogger();
	
	/** Holds the trace logger */
	private TraceLogger traceLogger = new DefaultTraceLogger();
	/** Holds the exception logger */
	private ExceptionLogger exceptionLogger;
	/** Holds the profile logger */
	private ProfileLogger profileLogger;
	
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
}
