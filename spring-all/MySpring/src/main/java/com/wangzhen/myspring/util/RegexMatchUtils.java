package com.wangzhen.myspring.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: Administrator
 * @Date: 2018-12-07 17:07
 * @Description:
 */
public class RegexMatchUtils {

    private static String[] validateExpresseion(String expression) throws Exception {
        Pattern compile = Pattern.compile("(?<=\\().+?\\)");
        Matcher matcher = compile.matcher(expression);
        if(!matcher.find()){
            throw new Exception("表达式错误。");
        }
        String[] split = matcher.group().split(" ");
        if(split.length <= 1){
            throw new Exception("表达式错误。");
        }
        return split;
    }

    /**
     * 获取表达式的权限符
     * @param expression
     * @return
     * @throws Exception
     */
    public static String matchModifier(String expression) throws Exception {
        String[] split = validateExpresseion(expression);
        return split[0];
    }

    /**
     * 匹配类名
     * @param expression
     * @return
     * @throws Exception
     */
    public static String matchClassName(String expression) throws Exception {
        return match(expression, "(.+?)(?=\\..+\\()");
    }

    public static String matchMethodParam(String expression) throws Exception {
        return match(expression, "(?<=\\()(.*?)(?=\\))");
    }

    public static String matchMethodName(String expression) throws Exception {
        return match(expression, "[^\\.]+?(?=\\()");
    }

    private static String match(String expression, String s) throws Exception {
        String[] split = validateExpresseion(expression);
        String pathName = split[1];
        Pattern compile = Pattern.compile(s);
        Matcher matcher = compile.matcher(pathName);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String s = matchMethodName("execution(* aop.pointcut.impl.RegexExpressionPointCutResolver.test(..))");
        System.out.println(s);
    }
}
