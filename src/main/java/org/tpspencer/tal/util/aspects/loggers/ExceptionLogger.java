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
