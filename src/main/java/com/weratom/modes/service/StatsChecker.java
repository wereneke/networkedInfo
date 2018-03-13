package com.weratom.modes.service;

import java.io.DataOutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class StatsChecker implements Runnable{

    private String response;

    public String getResponse() {
        return response;
    }

    public void run() {
        try {
            while (true) {
                checkUsage();
                Thread.sleep(500);
//                System.out.print("\033[H\033[2J");
//                System.out.flush();
            }
        } catch (Exception e) {}
    }

    private void checkUsage() {
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
                response = method.getName() + " = " + value;
            }
        }
    }
}
