package com.c0d3m4513r.config.qual;

import com.c0d3m4513r.config.iface.key.IConfigLoadable;
import com.c0d3m4513r.config.iface.key.IConfigLoadableSaveable;
import com.c0d3m4513r.config.iface.key.IConfigSavable;
import org.checkerframework.framework.qual.SubtypeOf;

import java.lang.annotation.*;

/**
 * This annotation is used to mark, that a type implements {@link IConfigSavable}, {@link IConfigLoadable} and {@link IConfigLoadableSaveable}.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf({LoadableNonSaveable.class, NonLoadableSavable.class})
public @interface LoadableSaveable {
}
