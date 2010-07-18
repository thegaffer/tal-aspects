package org.tpspencer.tal.util.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation indicates that a class should be
 * traced when it is executed.
 * 
 * @author Tom Spencer
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface Trace {
}
