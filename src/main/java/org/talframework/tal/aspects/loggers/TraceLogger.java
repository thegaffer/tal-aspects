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

package org.talframework.tal.aspects.loggers;

import org.aspectj.lang.JoinPoint;

/**
 * This logger represents the normal before/after a method
 * logging.
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
