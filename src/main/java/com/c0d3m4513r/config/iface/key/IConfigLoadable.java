package com.c0d3m4513r.config.iface.key;

import com.c0d3m4513r.config.qual.LoadableNonSaveable;

public interface IConfigLoadable {
    /***
     * Loads all necessary Config values, from a IConfigLoader.
     * By default, this method will try to call this method (loadValue) on any field that is annotated with {@link LoadableNonSaveable}.
     */
    default void loadValue() {
        ConfigLoadableSavableExecuter.executeLoadOrSave(LoadableNonSaveable.class, "loadValue", this);
    }
}
