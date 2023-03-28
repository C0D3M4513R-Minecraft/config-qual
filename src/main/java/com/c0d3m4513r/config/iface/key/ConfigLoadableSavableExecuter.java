package com.c0d3m4513r.config.iface.key;

import com.c0d3m4513r.logger.Logging;
import lombok.NonNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

final class ConfigLoadableSavableExecuter {
    /**
     * This is a helper method, that calls the given method name, on all fields in a given object that are annotated with the given annotation.
     * This tries to bypass the field access restrictions, by using {@link java.lang.reflect.AccessibleObject#setAccessible(boolean)}.
     * @param annotation The annotation to look for.
     * @param methodName The method name to call.
     * @param object The object instance to look for the fields in.
     */
    public static void executeLoadOrSave(@NonNull Class<? extends Annotation> annotation, @NonNull String methodName, @NonNull Object object){
        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(annotation))
                .forEach(f -> {
                    try {
                        try{
                            f.setAccessible(true);
                        }catch (Throwable e){
                            Logging.INSTANCE.warn("Could not set field '" + f.getName() + "' as accessible. Continuing anyways.", e);
                        }
                        Object fobj = f.get(object);
                        if (fobj != null){
                            f.getType().getMethod(methodName).invoke(fobj);
                        }else{
                            Logging.INSTANCE.error("Field '" + f.getName() + "' in class '" + object.getClass().getName() + "' is null. Cannot load value.");
                        }
                    } catch (NoSuchMethodException ignored) {
                    } catch (SecurityException e){
                        Logging.INSTANCE.info("[API] Could not set field '"+ f.getName()+"' of class '"+f.getType().getName()+"' as accessible. The exception was:", e);
                    }catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException | ExceptionInInitializerError e) {
                        Logging.INSTANCE.error("[API] Could not load value for field '" + f.getName() + "' in class '" + object.getClass().getName() + "' because of the following exception:", e);
                    }
                });
    }
}
