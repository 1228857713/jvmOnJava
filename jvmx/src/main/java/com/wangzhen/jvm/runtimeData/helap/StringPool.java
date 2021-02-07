package com.wangzhen.jvm.runtimeData.helap;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zachaxy
 * @date 17/12/30
 * desc:这里用来模拟 JVM 中的字符串池，但是由于当前的 JVM 本身就是用 Java 写的，所以会省掉很多真正的细节
 * 这里用一个 ConcurrentHasMap 来模拟字符串池，key 为从 class 文件中读到的字符串，value 为我们定义的 Zobject
 */
public class StringPool {
    public static Map<String,ZObject> internedString = new ConcurrentHashMap<>();
    public static Map<ZObject,String> realInternedString = new ConcurrentHashMap<>();

    public static ZObject jString(ZClassLoader classLoader,String str){
        if(internedString.containsKey(str)){
            return internedString.get(str);
        }
        char [] chars = str.toCharArray();
        ZObject jChars = new ZObject(classLoader.loadClass("[C"),chars,null);
        ZObject jStr = classLoader.loadClass("java/lang/String").newObject();
        jStr.setRefVar("value","[C",jChars);
        internedString.put(str,jStr);
        realInternedString.put(jStr,str);
        return jStr;
    }

    public static String realString(ZObject jstr){
        if (realInternedString.containsKey(jstr)){
            return realInternedString.get(jstr);
        }
        ZObject ref=jstr.getRefVar("value","[C");
        char [] chars = ref.getChars();
        String realStr = new String(chars);
        realInternedString.put(jstr,realStr);
        return realStr;

    }



}
