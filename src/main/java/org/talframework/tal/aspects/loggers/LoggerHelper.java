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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This helper class gets the logger for a specific class
 * and contains methods to log out values.
 *
 * @author Tom Spencer
 */
public final class LoggerHelper {
	
	/**
	 * Helper to get the logger for the class we are running.
	 * This is extracted because it may be slow and require
	 * some additional work.
	 * 
	 * @param cls The class to get logger for
	 * @return The logger
	 */
	public static Log getLogger(Class<?> cls) {
		return LogFactory.getLog(cls);
	}
	
	/**
	 * Helper to log out an object. This is separated so we
	 * can introduce reflection if the class does not declare
	 * a toString method.
	 * 
	 * @param o The object
	 * @return Its output value
	 */
	public static String getValue(Object o) {
	    return o != null ? o.toString() : null;
	}
	
	/**
	 * This method simply adds arguments to a string builder,
	 * creating a new line for each argument, indenting by 1
	 * tab.
	 * 
	 * @param args The arguments to log
	 * @param builder The existing string builder
	 */
	public static void appendArguments(Object[] args, StringBuilder builder) {
	    if( args != null && args.length > 0 ) {
            for( int i = 0 ; i < args.length ; i++ ) {
                builder.append("\n\targ[").append(i).append("]=").append(args[i]);
            }
        }
	}
}
