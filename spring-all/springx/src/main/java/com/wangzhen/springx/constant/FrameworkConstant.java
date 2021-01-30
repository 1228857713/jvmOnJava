package com.wangzhen.springx.constant;

import com.wangzhen.springx.util.ConfigHelper;

/**
 * Description:
 * Datetime:    2020/10/24   8:17 下午
 * Author:   王震
 */
public interface FrameworkConstant {

    String UTF_8 = "UTF-8";
    String CONFIG_PROPS = "smart.properties";
    String SQL_PROPS = "smart-sql.properties";
    String PLUGIN_PACKAGE = "org.smart4j.plugin";
    String JSP_PATH = ConfigHelper.getString("smart.framework.app.jsp_path", "/WEB-INF/jsp/");
    String WWW_PATH = ConfigHelper.getString("smart.framework.app.www_path", "/www/");
    String HOME_PAGE = ConfigHelper.getString("smart.framework.app.home_page", "/index.html");
    int UPLOAD_LIMIT = ConfigHelper.getInt("smart.framework.app.upload_limit", 10);

    String PK_NAME = "id";
}
