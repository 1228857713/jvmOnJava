package com.wangzhen.jvm.runtimeData.helap;

/**
 * @author zachaxy
 * @date 17/12/25
 */
public class MethodLookup {
    public static ZMethod lookupMethodInClass(ZClass clazz, String name, String descriptor) {
        ZClass c = clazz;
        while (c != null) {
            for (ZMethod method : c.methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
            c = c.superClass;
        }
        return null;
    }

    public static ZMethod lookupMethodInInterfaces(ZClass[] ifaces, String name, String descriptor) {
        for (ZClass iface : ifaces) {
            for (ZMethod method : iface.methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
            ZMethod method = lookupMethodInInterfaces(iface.interfaces, name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }
}
