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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

/**
 * This logger is used in the tests to hold the log output.
 * It can then be queries after the test.
 *
 * @author Tom Spencer
 */
public class SimpleLogger implements Log {
    
    private Map<String, List<String>> logs = new HashMap<String, List<String>>();
    
    public SimpleLogger(String arg) {
        
    }
    
    public void clear() {
        logs = new HashMap<String, List<String>>();
    }
    
    public int getLogSize() {
        int ret = 0;
        if( logs.size() == 0 ) return ret;
        
        ret += getLogSize("trace");
        ret += getLogSize("debug");
        ret += getLogSize("info");
        ret += getLogSize("warn");
        ret += getLogSize("error");
        ret += getLogSize("fatal");
        
        return ret;
    }
    
    /**
     * Helper to get the number of entries at a level
     * 
     * @param level The level
     * @return The number at that level
     */
    public int getLogSize(String level) {
        List<String> logs = getLogs(level, false);
        return logs != null ? logs.size() : 0;
    }
    
    /**
     * Helper to get the logs at a particular level
     * 
     * @param level The level
     * @param create Create if the logs don't exist
     * @return The list of logs
     */
    public List<String> getLogs(String level, boolean create) {
        List<String> ret = logs.get(level);
        if( create && ret == null ) {
            ret = new ArrayList<String>();
            logs.put(level, ret);
        }
        return ret;
    }
    
    ///////////////////////////////////////
    
    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isTraceEnabled()
     */
    public boolean isTraceEnabled() {
        return true;
    }
    
    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isDebugEnabled()
     */
    public boolean isDebugEnabled() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isInfoEnabled()
     */
    public boolean isInfoEnabled() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isWarnEnabled()
     */
    public boolean isWarnEnabled() {
        return true;
    }
    
    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isErrorEnabled()
     */
    public boolean isErrorEnabled() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#isFatalEnabled()
     */
    public boolean isFatalEnabled() {
        return true;
    }

    
    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#trace(java.lang.Object, java.lang.Throwable)
     */
    public void trace(Object val, Throwable t) {
        trace(val);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#trace(java.lang.Object)
     */
    public void trace(Object val) {
        List<String> logs = getLogs("trace", true);
        logs.add(val.toString());
        System.out.println("[TRACE] - " + val.toString());
    }
    
    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#debug(java.lang.Object, java.lang.Throwable)
     */
    public void debug(Object val, Throwable t) {
        debug(val);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#debug(java.lang.Object)
     */
    public void debug(Object val) {
        List<String> logs = getLogs("debug", true);
        logs.add(val.toString());
        System.out.println("[DEBUG] - " + val.toString());
    }
    
    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#info(java.lang.Object, java.lang.Throwable)
     */
    public void info(Object val, Throwable t) {
        info(val);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#info(java.lang.Object)
     */
    public void info(Object val) {
        List<String> logs = getLogs("info", true);
        logs.add(val.toString());
        System.out.println("[INFO] - " + val.toString());
    }
    
    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#warn(java.lang.Object, java.lang.Throwable)
     */
    public void warn(Object val, Throwable t) {
        warn(val);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#warn(java.lang.Object)
     */
    public void warn(Object val) {
        List<String> logs = getLogs("warn", true);
        logs.add(val.toString());
        System.err.println("[WARN] - " + val.toString());
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#error(java.lang.Object, java.lang.Throwable)
     */
    public void error(Object val, Throwable t) {
        error(val);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#error(java.lang.Object)
     */
    public void error(Object val) {
        List<String> logs = getLogs("error", true);
        logs.add(val.toString());
        System.err.println("[ERROR] - " + val.toString());
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#fatal(java.lang.Object, java.lang.Throwable)
     */
    public void fatal(Object val, Throwable t) {
        fatal(val);
    }

    /* (non-Javadoc)
     * @see org.apache.commons.logging.Log#fatal(java.lang.Object)
     */
    public void fatal(Object val) {
        List<String> logs = getLogs("fatal", true);
        logs.add(val.toString());
        System.err.println("[FATAL] - " + val.toString());
    }
}
