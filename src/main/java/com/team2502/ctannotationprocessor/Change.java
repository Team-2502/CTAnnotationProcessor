package com.team2502.ctannotationprocessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marking stuff that needs to be changed.
 */
@Target({ ElementType.TYPE,
          ElementType.FIELD,
          ElementType.METHOD,
          ElementType.PARAMETER,
          ElementType.CONSTRUCTOR,
          ElementType.LOCAL_VARIABLE,
          ElementType.ANNOTATION_TYPE,
          ElementType.PACKAGE })
@Retention(RetentionPolicy.SOURCE)
public @interface Change
{
    String reason() default "Flex Seal";
}
