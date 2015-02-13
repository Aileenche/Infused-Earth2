package com.aileen.ie2.material;

import com.aileen.ie2.handler.DataHandler;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by Aileen on 31.01.2015.
 */
public class Materials {
    public static void init() {
        DataHandler.TITANIUM = EnumHelper.addToolMaterial("TITANIUM", 5, 5000, 10F, 10F, 0);
        DataHandler.SELENIUM = EnumHelper.addToolMaterial("SELENIUM", 4, 2500, 7F, 7F, 0);
        DataHandler.RUBY = EnumHelper.addToolMaterial("RUBY", 4, 1500, 5F, 5F, 0);
        DataHandler.SAPPHIRE = EnumHelper.addToolMaterial("SAPPHIRE", 3, 1100, 4F, 4F, 0);
        DataHandler.AMETHYST = EnumHelper.addToolMaterial("AMETHYST", 2, 750, 3F, 3F, 0);
        DataHandler.TOPAS = EnumHelper.addToolMaterial("TOPAS", 1, 500, 2F, 2F, 0);
    }
}
