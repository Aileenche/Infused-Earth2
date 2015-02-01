package com.aileen.ie2.material;

import com.aileen.ie2.handler.DataHandler;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by Aileen on 31.01.2015.
 */
public class Materials {
    public static void init() {
        DataHandler.SELENIUM = EnumHelper.addToolMaterial("SELENIUM", 4, 100000, 20F, 20F, 30);
        DataHandler.RUBY = EnumHelper.addToolMaterial("RUBY", 4, 50000, 20F, 10F, 30);
        DataHandler.SAPPHIRE = EnumHelper.addToolMaterial("SAPPHIRE", 4, 10000, 20F, 10F, 30);
        DataHandler.AMETHYST = EnumHelper.addToolMaterial("AMETHYST", 4, 5000, 20F, 10F, 30);
        DataHandler.TOPAS = EnumHelper.addToolMaterial("TOPAS", 4, 5000, 20F, 10F, 30);
    }
}
