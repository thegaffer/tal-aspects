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
package org.talframework.tal.aspects.loggers.trace;

import org.apache.commons.logging.Log;
import org.aspectj.lang.JoinPoint;
import org.talframework.tal.aspects.loggers.LoggerHelper;
import org.talframework.tal.aspects.loggers.TraceLogger;

/**
 * This class is the default warning logger that warns when
 * a particular method is called that would not ordinarily
 * be called.
 *
 * @author Tom Spencer
 */
public final class DefaultWarnLogger implements TraceLogger {
    
    /**
     * Logs out a warning that the method has been called.
     */
    public void traceBefore(JoinPoint jp) {
        Log traceLogger = LoggerHelper.getLogger(jp.getStaticPart().getSignature().getDeclaringType());
        if( traceLogger.isWarnEnabled() ) {
            StringBuilder builder = new StringBuilder();
            builder.append("!!! ").append(jp.getStaticPart().getSignature().getName());
            LoggerHelper.appendArguments(jp.getArgs(), builder);
            
            traceLogger.warn(builder.toString());
        }
    }

    /**
     * Does nothing as will not be called anyway
     */
    public void traceAfter(JoinPoint jp, Object retVal) {
        
    }
}
