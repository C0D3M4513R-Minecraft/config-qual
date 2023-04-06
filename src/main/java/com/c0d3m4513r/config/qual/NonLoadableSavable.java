package com.c0d3m4513r.config.qual;

import com.c0d3m4513r.config.iface.key.IConfigLoadable;
import com.c0d3m4513r.config.iface.key.IConfigSavable;
import org.checkerframework.framework.qual.SubtypeOf;

import java.lang.annotation.*;

/**
 * This annotation is used to mark, that a type implements {@link IConfigSavable}, but not {@link IConfigLoadable}.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({NonLoadableNonSaveable.class})
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
public @interface NonLoadableSavable {
}
