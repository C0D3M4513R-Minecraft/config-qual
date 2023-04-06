package com.c0d3m4513r.config.iface.key;

import com.c0d3m4513r.config.qual.LoadableNonSaveable;
import com.c0d3m4513r.config.qual.LoadableSaveable;
import com.c0d3m4513r.config.qual.NonLoadableSavable;
import com.c0d3m4513r.logger.Logging;
import lombok.NonNull;
import org.checkerframework.common.reflection.qual.ClassBound;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

final class ConfigLoadableSavableExecuter {

    /**
     * {@link IConfigLoadable#loadValue()} all Fields (including static ones), that are annotated with {@link LoadableNonSaveable} or {@link LoadableSaveable}.
     * @param object The object containing the fields.
     */
    public static void executeLoad(@NonNull IConfigLoadable object){
        executeLoadOrSave(
                f -> f.isAnnotationPresent(LoadableNonSaveable.class) || f.isAnnotationPresent(LoadableSaveable.class),
                (o,f) -> {
                    try{
                        IConfigLoadable loadable = (IConfigLoadable) o;
                        loadable.loadValue();
                    }catch (ClassCastException e){
                        Logging.INSTANCE.error("[config-qual] Could not load field '{}' of class '{}', because the Class does not implement IConfigLoadable.", f.getName(), f.getType().getName());
                        Logging.INSTANCE.error("[config-qual] Exception was: ", e);
                    }
                }, object
        );
    }

    /**
     * {@link IConfigSavable#saveValue()} all Fields (including static ones), that are annotated with {@link NonLoadableSavable} or {@link LoadableSaveable}.
     * @param object The object containing the fields.
     */
    public static void executeSave(@NonNull IConfigSavable object){
        executeLoadOrSave(
                f -> f.isAnnotationPresent(NonLoadableSavable.class) || f.isAnnotationPresent(LoadableSaveable.class),
                (o,f) -> {
                    try{
                        IConfigSavable loadable = (IConfigSavable) o;
                        loadable.saveValue();
                    }catch (ClassCastException e){
                        Logging.INSTANCE.error("[config-qual] Could not save field '{}' of class '{}', because the Class does not implement IConfigLoadable.", f.getName(), f.getType().getName());
                        Logging.INSTANCE.error("[config-qual] Exception was: ", e);
                    }
                }, object
        );
    }
    /**
     * This is a helper method, that calls the given method name, on all fields in a given object that are annotated with the given annotation.
     * This tries to bypass the field access restrictions, by using {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)}.
     * @param predicate The fields to look for.
     * @param consumer The action to take on the fields.
     * @param object The object instance to look for the fields in.
     */
    private static void executeLoadOrSave(@NonNull Predicate<? super Field> predicate,
                                          @NonNull BiConsumer<
                                                  @ClassBound({"com.c0d3m4513r.config.iface.key.IConfigLoadable", "com.c0d3m4513r.config.iface.key.IConfigSavable"})
                                                  @org.checkerframework.checker.nullness.qual.NonNull
                                                          Object,
                                                  @org.checkerframework.checker.nullness.qual.NonNull
                                                          Field
                                          > consumer,
                                          @ClassBound({"com.c0d3m4513r.config.iface.key.IConfigLoadable", "com.c0d3m4513r.config.iface.key.IConfigSavable"}) @NonNull Object object
    ){
        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(predicate)
                .forEach(f -> {
                    try {
                        try{
                            f.setAccessible(true);
                        }catch (Throwable e){
                            Logging.INSTANCE.warn("[config-qual] Could not set field '" + f.getName() + "' as accessible. Continuing anyways.", e);
                        }
                        Object fobj = f.get(object);
                        if (fobj != null){
                            consumer.accept(fobj, f);
                        }else{
                            Logging.INSTANCE.error("[config-qual] Field '{}' in class '{}' is null. Cannot load value.", f.getName(), object.getClass().getName());
                        }
                    } catch (SecurityException e){
                        Logging.INSTANCE.info("[config-qual] Could not set field '"+ f.getName()+"' of class '"+f.getType().getName()+"' as accessible. The exception was:", e);
                    }catch (IllegalAccessException | IllegalArgumentException | ExceptionInInitializerError e) {
                        Logging.INSTANCE.error("[config-qual] Could not load value for field '" + f.getName() + "' in class '" + object.getClass().getName() + "' because of the following exception:", e);
                    }
                });
    }
}
