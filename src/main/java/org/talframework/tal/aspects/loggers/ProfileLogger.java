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
package org.talframework.tal.aspects.loggers;

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
