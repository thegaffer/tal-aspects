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
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.talframework.tal.aspects.loggers.ConstructionLogger;

/**
 * Logs out the construction of a prototype object
 * at warn level.
 *
 * @author Tom Spencer
 */
public class DefaultPrototypeLogger implements ConstructionLogger {
    private static final Log logger = LogFactory.getLog(DefaultPrototypeLogger.class);

    /**
     * Simply warns on the usage at construction time
     */
    public void traceCreation(JoinPoint jp) {
        logger.warn("!!! Prototype object created, change for production: " + jp.getTarget().getClass());
    }
}
