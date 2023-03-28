package com.c0d3m4513r.config.qual;

import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeKind;
import org.checkerframework.framework.qual.UpperBoundFor;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark a type as unknown, if it is loadable or savable.
 */
@Documented
@SubtypeOf({})
@DefaultQualifierInHierarchy
@Target({})
@UpperBoundFor(
        typeKinds = {
                TypeKind.BOOLEAN,
                TypeKind.BYTE,
                TypeKind.SHORT,
                TypeKind.INT,
                TypeKind.LONG,
                TypeKind.CHAR,
                TypeKind.FLOAT,
                TypeKind.DOUBLE,
                TypeKind.VOID,
                TypeKind.NONE,
                TypeKind.NULL,
                TypeKind.ARRAY,
        })
public @interface NonLoadableNonSaveable {
}
