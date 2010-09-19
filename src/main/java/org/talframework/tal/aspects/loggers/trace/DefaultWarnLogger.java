/*
 * Copyright 2010 Thomas Spencer
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
