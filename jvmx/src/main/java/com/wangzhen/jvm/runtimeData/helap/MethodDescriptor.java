package com.wangzhen.jvm.runtimeData.helap;

import java.util.ArrayList;

/**
 * @author zachaxy
 * @date 17/12/27
 */
public class MethodDescriptor {
    private String raw;             //在class文件中原始的描述eg:(Ljava/lang/String;IL)V
    private int offset;             //解析raw的当前索引

    private ArrayList<String> parameterTypes;
    private String returnType;

    public MethodDescriptor(String raw) {
        this.raw = raw;
        parameterTypes = new ArrayList<String>();
        parse();
    }

    private void addParameterType(String type) {
        parameterTypes.add(type);
    }

    private void parse() {
        startParams();
        parseParamTypes();
        endParams();
        parseReturnType();
        finish();
    }

    //参数无论有无,都需要用(开始的
    private void startParams() {
        if ('(' != readChar()) {
            causePanic();
        }
    }


    //参数无论有无,都需要用)结束的
    private void endParams() {
        if (')' != readChar()) {
            causePanic();
        }
    }

    //解析结束后offset应该等于字符串的长度,都则报错!
    private void finish() {
        if (offset != raw.length()) {
            causePanic();
        }
    }


    private void causePanic() {
        throw new RuntimeException("BAD descriptor: " + raw);
    }


    //获取当前字符串的一个字符;
    private char readChar() {
        char c = raw.charAt(offset);
        offset++;
        return c;
    }

    //字符解析回退,只将当前指针-1即可
    private void unreadChar() {
        offset--;
    }


    //解析参数类型，解析到之后不断的向parameterTypes添加解析到的类型
    private void parseParamTypes() {
        while (true) {
            String t = parseFieldType();
            if (!"".equals(t)) {
                parameterTypes.add(t);
            } else {
                break;
            }
        }
    }

    //解析返回值类型
    private void parseReturnType() {
        if ('V' == readChar()) {
            returnType = "V";
            return;
        }

        unreadChar();
        String t = parseFieldType();
        if (!"".equals(t)) {
            returnType = t;
            return;
        }

        causePanic();
    }

    //解析参数或返回值的具体类型
    private String parseFieldType() {
        char c = readChar();
        switch (c) {
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'J':
                return "J";
            case 'S':
                return "S";
            case 'Z':
                return "Z";
            case 'L':
                return parseObjectType();
            case '[':
                return parseArrayType();
            default://如果产生这种情况,说明参数已经解析完毕了,目前的符号必定是),回退1,调用解析参数结束
                unreadChar();
                return "";
        }
    }

    //如果是引用类型,其必定是以;结尾的----最终的返回值是形如:Ljava/lang/String的形式;
    private String parseObjectType() {
        int semicolonIndex = raw.indexOf(';', offset);
        if (semicolonIndex == -1) {
            causePanic();
            return "";
        } else {
            int start = offset - 1;
            int end = semicolonIndex + 1;
            offset = end;
            return raw.substring(start, end);
        }
    }

    //解析数组类型
    private String parseArrayType() {
        int start = offset - 1;
        parseFieldType();
        int end = offset;
        return raw.substring(start, end);
    }


    public ArrayList<String> getParameterTypes() {
        return parameterTypes;
    }

    public String getReturnType() {
        return returnType;
    }
}
