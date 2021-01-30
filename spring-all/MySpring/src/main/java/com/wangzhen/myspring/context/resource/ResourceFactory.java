package com.wangzhen.myspring.context.resource;

/**
 * Description:
 * Datetime:    2020/11/2   11:10
 * Author:   王震
 */
public interface ResourceFactory {
    Resource getResource(String localtions) throws Exception;
}