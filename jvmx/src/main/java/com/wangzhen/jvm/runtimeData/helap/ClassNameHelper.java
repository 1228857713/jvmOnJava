package com.wangzhen.jvm.runtimeData.helap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zachaxy
 * @Desc
 * @date 17/12/29
 */
public class ClassNameHelper {
    public static HashMap<String, String> primitiveTypes;

    static {
        primitiveTypes = new HashMap<String, String>();
        primitiveTypes.put("void", "V");
        primitiveTypes.put("boolean", "Z");
        primitiveTypes.put("byte", "B");
        primitiveTypes.put("short", "S");
        primitiveTypes.put("int", "I");
        primitiveTypes.put("long", "J"); // L 已经被对象使用掉了，所以这里使用 J 代表long
        primitiveTypes.put("char", "C");
        primitiveTypes.put("float", "F");
        primitiveTypes.put("double", "D");
    }

    // [XXX -> [[XXX
    // int -> [I
    // XXX -> [LXXX;
    public static String getArrayClassName(String className) {
        return "[" + toDescriptor(className);
    }

    // [[XXX -> [XXX
    // [LXXX; -> XXX
    // [I -> int
    public static String getComponentClassName(String className) {
        if (className.charAt(0) == '[') {
            String componentTypeDescriptor = className.substring(1);
            return toClassName(componentTypeDescriptor);
        }
        throw new RuntimeException("Not array: " + className);
    }

    // [XXX => [XXX
    // int  => I
    // XXX  => LXXX;
    private static String toDescriptor(String className) {
        if (className.charAt(0) == '[') {
            //array
            return className;
        }
        if (primitiveTypes.containsKey(className)) {
            return primitiveTypes.get(className);
        }

        // object
        return "L" + className + ";";
    }

    // [XXX  => [XXX
    // LXXX; => XXX
    // I     => int
    private static String toClassName(String descriptor) {
        if (descriptor.charAt(0) == '[') {
            // array
            return descriptor;
        }
        if (descriptor.charAt(0) == 'L') {
            // object
            return descriptor.substring(1, descriptor.length() - 1);
        }
        for (Map.Entry<String, String> entry : primitiveTypes.entrySet()) {
            if (entry.getValue().equals(descriptor)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("Invalid descriptor: " + descriptor);
    }

}
