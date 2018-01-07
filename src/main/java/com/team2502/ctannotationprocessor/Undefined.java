package com.team2502.ctannotationprocessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to annotate a RobotMap id field to notify user
 * that the code may not work correctly or trigger an error.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface Undefined
{
    /**
     * @return whether or not it is safe for this
     *          variable to be undefined.
     */
    boolean safe() default false;
}
