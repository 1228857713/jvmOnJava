package com.wangzhen.springx.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 *
 * @author huangyong
 * @since 1.0
 */
public class StringUtil {

    /**
     * 字符串分隔符
     */
    public static final String SEPARATOR = String.valueOf((char) 29);

    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * 若字符串为空，则取默认值
     */
    public static String defaultIfEmpty(String str, String defaultValue) {
        return StringUtils.defaultIfEmpty(str, defaultValue);
    }

    /**
     * 替换固定格式的字符串（支持正则表达式）
     */
    public static String replaceAll(String str, String regex, String replacement) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, replacement);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * 是否为数字（整数或小数）
     */
    public static boolean isNumber(String str) {
        return NumberUtils.isNumber(str);
    }

    /**
     * 是否为十进制数（整数）
     */
    public static boolean isDigits(String str) {
        return NumberUtils.isDigits(str);
    }

    /**
     * 将驼峰风格替换为下划线风格
     */
    public static String camelhumpToUnderline(String str) {
        Matcher matcher = Pattern.compile("[A-Z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() + i, matcher.end() + i, "_" + matcher.group().toLowerCase());
        }
        if (builder.charAt(0) == '_') {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    /**
     * 将下划线风格替换为驼峰风格
     */
    public static String underlineToCamelhump(String str) {
        Matcher matcher = Pattern.compile("_[a-z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }

    /**
     * 分割固定格式的字符串
     */
    public static String[] splitString(String str, String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }

    /**
     * 将字符串首字母大写
     */
    public static String firstToUpper(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 将字符串首字母小写
     */
    public static String firstToLower(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 转为帕斯卡命名方式（如：FooBar）
     */
    public static String toPascalStyle(String str, String seperator) {
        return StringUtil.firstToUpper(toCamelhumpStyle(str, seperator));
    }

    /**
     * 转为驼峰命令方式（如：fooBar）
     */
    public static String toCamelhumpStyle(String str, String seperator) {
        return StringUtil.underlineToCamelhump(toUnderlineStyle(str, seperator));
    }

    /**
     * 转为下划线命名方式（如：foo_bar）
     */
    public static String toUnderlineStyle(String str, String seperator) {
        str = str.trim().toLowerCase();
        if (str.contains(seperator)) {
            str = str.replace(seperator, "_");
        }
        return str;
    }

    /**
     * 转为显示命名方式（如：Foo Bar）
     */
    public static String toDisplayStyle(String str, String seperator) {
        String displayName = "";
        str = str.trim().toLowerCase();
        if (str.contains(seperator)) {
            String[] words = StringUtil.splitString(str, seperator);
            for (String word : words) {
                displayName += StringUtil.firstToUpper(word) + " ";
            }
            displayName = displayName.trim();
        } else {
            displayName = StringUtil.firstToUpper(str);
        }
        return displayName;
    }
}
