package com.weratom.modes.service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class StatsChecker implements Runnable{

    private String response;
    Thread statsCheckerThread;
    private int responsivenessSpeed = 5000;

    public String getResponse() {
        return response;
    }

    public void run() {

        statsCheckerThread = Thread.currentThread();
        try {
            while (true) {
                checkUsage();
                Thread.sleep(responsivenessSpeed);
            }
        } catch (Exception e) {}
    }

    private void checkUsage() {
        StringBuilder currentStats = new StringBuilder();
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().startsWith("get")
                    && Modifier.isPublic(method.getModifiers())) {
                Object value;
                try {
                    value = method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = e;
                }
                currentStats.append(method.getName());
                currentStats.append(" = ");
                currentStats.append(value);
                currentStats.append("\n");
            }
        }
        response = currentStats.toString();
    }

    public void setResponsivenessSpeed(int val) {
        this.responsivenessSpeed = val;
    }

    public int getResponsivenessSpeed() {
        return this.responsivenessSpeed;
    }
}
