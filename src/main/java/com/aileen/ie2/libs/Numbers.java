package com.aileen.ie2.libs;

import java.text.DecimalFormat;

/**
 * Created by Aileen on 11.02.2015.
 */
public class Numbers {
    public static String los(int zahl) {
        DecimalFormat nf = new DecimalFormat();
        String ausgabe = nf.format(zahl);
        return ausgabe;

    }
}
