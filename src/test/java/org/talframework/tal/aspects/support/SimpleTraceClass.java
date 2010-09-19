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

package org.talframework.tal.aspects.support;

import org.talframework.tal.aspects.annotations.Trace;
import org.talframework.tal.aspects.annotations.TraceWarn;

/**
 * This class doesn't do anything, but includes methods that
 * are marked as trace.
 *
 * @author Tom Spencer
 */
public class SimpleTraceClass {

    @Trace
    public String basic(String arg0, int arg1) {
        return "basic";
    }
    
    @Trace
    public void noReturn(double arg0) {
        
    }
    
    @Trace
    public String noArgs() {
        return "noArgs";
    }
    
    @Trace
    public String fails(String arg0, int arg1) {
        throw new IllegalArgumentException();
    }
    
    public String noTrace(String arg0, int arg1) {
        return "noTrace";
    }
 
    @TraceWarn
    public void shouldntBeCalled(String arg0) {
        
    }
}
