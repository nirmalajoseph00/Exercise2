package com.zohocorp.zohoex2project;

public class ConstructorInvoker {
	static {
        System.loadLibrary("constructorInvoker");
    }

    public static native void invoke(Object instance);

}
