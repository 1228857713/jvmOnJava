package com.wangzhen.javastudy.jvmTest.G1Demo;

/**
 * Description:
 * Datetime:    2020/12/22   下午8:19
 * Author:   王震
 */
public class GreenhouseScheduler {
    private volatile boolean light = false ;
    private volatile boolean water = false ;
    private String thermostat ="Day";

    public synchronized String getThermostat() {
        return thermostat ;
    }

    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
