package org.tpspencer.tal.util.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation indicates that methods on a class
 * should be monitored for exceptions, but not traced
 * out generally.
 * 
 * @author Tom Spencer
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface TraceException {
}
