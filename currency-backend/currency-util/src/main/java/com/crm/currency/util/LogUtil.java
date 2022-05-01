package com.crm.currency.util;

import com.crm.currency.bean.LogBean;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    public static void debug(Class<?> clazz, Object message) {
        debug(clazz, message, null);
    }


    public static void debug(Class<?> clazz, Object message, Throwable te) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s", String.format("[%s]", Thread.currentThread().getName()), message));

        Logger logger = LogManager.getLogger(clazz);
        try {
            if (te == null)
                logger.debug(sb);
            else
                logger.debug(sb, te);
        } catch (Throwable e) {
            // e.printStackTrace();
        }
    }

    public static void info(Class<?> clazz, Object message) {
        info(clazz, message, null);
    }

    public static void info(Class<?> clazz, Object message, Throwable te) {

        Logger logger = LogManager.getLogger(clazz);
        LogBean logBean = new LogBean(message, null, te, "INFO");

        try {
            Gson gson = new Gson();
            String logMsg = gson.toJson(logBean);
            logger.info(logMsg);
        } catch (Throwable e) {
            // 寫 Log 出錯不做任何處裡
        }
    }


    public static void warn(Class<?> clazz, Object message) {
        warn(clazz, null, message);
    }

    public static void warn(Class<?> clazz, String msgCode, Object message) {
        warn(clazz, msgCode, message, null);
    }

    public static void warn(Class<?> clazz, String msgCode, Object message, Throwable te) {

        Logger logger = LogManager.getLogger(clazz);
        LogBean logBean = new LogBean(message, msgCode, te, "WARN");

        try {
            Gson gson = new Gson();
            String logMsg = gson.toJson(logBean);
            logger.warn(logMsg);
        } catch (Throwable e) {
            // 寫 Log 出錯不做任何處裡
        }
    }


    public static void error(Class<?> clazz, Object message) {
        error(clazz, null, message);
    }

    public static void error(Class<?> clazz, String msgCode, Object message) {
        error(clazz, msgCode, message, null);
    }

    public static void error(Class<?> clazz, String msgCode, Object message, Throwable te) {

        Logger logger = LogManager.getLogger(clazz);
        LogBean logBean = new LogBean(message, msgCode, te, "ERROR");

        try {
            Gson gson = new Gson();
            String logMsg = gson.toJson(logBean);
            logger.error(logMsg);
        } catch (Throwable e) {
            // 寫 Log 出錯不做任何處裡
        }
    }


}
