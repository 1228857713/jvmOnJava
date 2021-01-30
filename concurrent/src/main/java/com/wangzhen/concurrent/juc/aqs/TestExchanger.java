package com.wangzhen.concurrent.juc.aqs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Exchanger;

/**
 * Description:
 * Datetime:    2020/12/15   10:58
 * Author:   王震
 */
@Slf4j
public class TestExchanger {

    @Test
    public void test1(){
        Exchanger<String> stringExchanger = new Exchanger<>();
    }
}