package com.aileen.ie2.configuration;

import com.aileen.ie2.libs.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Aileen on 04.01.14.
 */
public class ConfigurationHandler {
    public static Configuration config;

    public static void init(File file) {
        config = new Configuration(file);
        config.load();
    }

    public static int getBiomeValue(String unlocalizedName) {
        int retval = config.get(Reference.CONFIG_CAT_BIOME, unlocalizedName, initialConfiguration.getValue(unlocalizedName)).getInt();
        return retval;
    }

    public static int getOreValue(String queryValue, int defaultvalue,String comment) {
        int retval = config.get(Reference.CONFIG_CAT_ORE, queryValue, defaultvalue,comment).getInt();
        return retval;
    }
    public static int getOreValue(String queryValue, int defaultvalue) {
        int retval = config.get(Reference.CONFIG_CAT_ORE, queryValue, defaultvalue).getInt();
        return retval;
    }

    public static void save() {
        config.save();
    }

}
