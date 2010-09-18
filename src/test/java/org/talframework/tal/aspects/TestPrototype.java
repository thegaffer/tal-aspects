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

package org.talframework.tal.aspects;

import junit.framework.Assert;

import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.talframework.tal.aspects.loggers.trace.DefaultPrototypeLogger;
import org.talframework.tal.aspects.support.PrototypeClass;
import org.talframework.tal.aspects.support.SimpleLogger;

public class TestPrototype {

    @Test
    public void basic() {
        new PrototypeClass();
        
        SimpleLogger logger = (SimpleLogger)LogFactory.getLog(DefaultPrototypeLogger.class);
        Assert.assertEquals(1, logger.getLogSize());
        Assert.assertEquals(1, logger.getLogSize("warn"));
    }
}
