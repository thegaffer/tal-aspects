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
