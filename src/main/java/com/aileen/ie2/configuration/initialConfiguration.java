package com.aileen.ie2.configuration;

import java.util.HashMap;

/**
 * Created by Aileen on 04.01.14.
 */
public class initialConfiguration {
    private static HashMap<String, Integer> IDList = new HashMap<String, Integer>();

    public static void init() {
        IDList.put("infusedBiomeID", 115);

    }

    public static int getValue(String unlocalizedName) {
        return IDList.get(unlocalizedName);
    }
}