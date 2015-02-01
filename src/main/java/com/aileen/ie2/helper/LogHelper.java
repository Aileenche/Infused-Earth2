package com.aileen.ie2.helper;

import com.aileen.ie2.libs.Reference;
import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Logger;


/**
 * Created by Aileen on 04.01.14.
 */
public class LogHelper {

    public static Logger logHelper;
    public static void init() {
        logHelper = FMLLog.getLogger();
    }

    public static void error(String text) {
        logHelper.info("[ " + Reference.MOD_ID.toUpperCase() + " ][ ERROR ] " + text);
    }

    public static void warning(String text) {
        logHelper.info("[ " + Reference.MOD_ID.toUpperCase() + " ][ WARNING ] " + text);
    }

    public static void debug(String text) {
        logHelper.info("[ " + Reference.MOD_ID.toUpperCase() + " ][ DEBUG ] " + text);
    }

    public static void info(String text) {
        logHelper.info("[ " + Reference.MOD_ID.toUpperCase() + " ][ INFO ] " + text);
    }

    public static void text(String text) {
        logHelper.info("[ " + Reference.MOD_ID.toUpperCase() + " ] " + text);
    }
}
