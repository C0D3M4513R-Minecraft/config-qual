package com.c0d3m4513r.config.iface.key;

import com.c0d3m4513r.config.qual.NonLoadableSavable;
import org.checkerframework.dataflow.qual.SideEffectFree;

public interface IConfigSavable {
    /***
     * Saves all necessary Config values, to a IConfigSaver.
     * By default, this method will try to call this method (saveValue) on any field that is annotated with {@link NonLoadableSavable}.
     */
    @SideEffectFree
    @SuppressWarnings({"purity.more.pure","purity.more.deterministic","purity.not.sideeffectfree.call"})
    default void saveValue(){
        ConfigLoadableSavableExecuter.executeSave(this);
    }
}
