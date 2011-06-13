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
