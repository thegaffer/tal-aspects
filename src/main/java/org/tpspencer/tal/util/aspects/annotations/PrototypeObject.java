package org.tpspencer.tal.util.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation marks a class as being a Prototype class.
 * A warning is output when this is used to prompt the 
 * developer to replace in a live/production system.
 * 
 * @author Tom Spencer
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface PrototypeObject {

}
