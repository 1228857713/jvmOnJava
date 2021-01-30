package com.wangzhen.springx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 属性文件操作工具类
 *
 * @author huangyong
 * @since 1.0
 */
public class PropsUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     */
    public static Properties loadProps(String propsPath) {
        Properties props = new Properties();
        InputStream is = null;
        try {
            if (StringUtil.isEmpty(propsPath)) {
                throw new IllegalArgumentException();
            }
            String suffix = ".properties";
            if (propsPath.lastIndexOf(suffix) == -1) {
                propsPath += suffix;
            }
            is = ClassUtil.getClassLoader().getResourceAsStream(propsPath);
            if (is != null) {
                props.load(is);
            }
        } catch (Exception e) {
            logger.error("加载属性文件出错！", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("释放资源出错！", e);
            }
        }
        return props;
    }

    /**
     * 加载属性文件，并转为 Map
     */
    public static Map<String, String> loadPropsToMap(String propsPath) {
        Map<String, String> map = new HashMap<String, String>();
        Properties props = loadProps(propsPath);
        for (String key : props.stringPropertyNames()) {
            map.put(key, props.getProperty(key));
        }
        return map;
    }

    /**
     * 获取字符型属性
     */
    public static String getString(Properties props, String key) {
        String value = "";
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取字符型属性（带有默认值）
     */
    public static String getString(Properties props, String key, String defalutValue) {
        String value = defalutValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取数值型属性
     */
    public static int getNumber(Properties props, String key) {
        int value = 0;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    // 获取数值型属性（带有默认值）
    public static int getNumber(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔型属性
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * 获取布尔型属性（带有默认值）
     */
    public static boolean getBoolean(Properties props, String key, boolean defalutValue) {
        boolean value = defalutValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取指定前缀的相关属性
     */
    public static Map<String, Object> getMap(Properties props, String prefix) {
        Map<String, Object> kvMap = new LinkedHashMap<String, Object>();
        Set<String> keySet = props.stringPropertyNames();
        if (CollectionUtil.isNotEmpty(keySet)) {
            for (String key : keySet) {
                if (key.startsWith(prefix)) {
                    String value = props.getProperty(key);
                    kvMap.put(key, value);
                }
            }
        }
        return kvMap;
    }
}
