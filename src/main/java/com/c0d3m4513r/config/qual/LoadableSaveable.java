package com.c0d3m4513r.config.qual;

import org.checkerframework.framework.qual.SubtypeOf;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf({LoadableNonSaveable.class, NonLoadableSavable.class})
public @interface LoadableSaveable {
}
