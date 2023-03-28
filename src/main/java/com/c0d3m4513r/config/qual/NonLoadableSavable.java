package com.c0d3m4513r.config.qual;

import org.checkerframework.framework.qual.SubtypeOf;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({NonLoadableNonSaveable.class})
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
//todo: write a checker, that checks if this annotation has been applied to a IConfigSavable
public @interface NonLoadableSavable {
}
