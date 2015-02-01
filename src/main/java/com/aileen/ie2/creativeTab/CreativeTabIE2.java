package com.aileen.ie2.creativeTab;

import com.aileen.ie2.handler.DataHandler;
import com.aileen.ie2.item.ModItems;
import com.aileen.ie2.libs.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Aileen on 04.01.14.
 */
public class CreativeTabIE2 {
    public static final CreativeTabs blocks = new CreativeTabs(Reference.MOD_ID.toLowerCase()+".blocks") {
        @Override
        public Item getTabIconItem() {
            return DataHandler.item_dustSelenium;
        }
    };
//    public static final CreativeTabs items = new CreativeTabs(Reference.MOD_ID.toLowerCase()+".items") {
//        @Override
//        public Item getTabIconItem() {
//            return DataHandler.crushed_Selenium_Ore;
//        }
//    };
//    public static final CreativeTabs network = new CreativeTabs(Reference.MOD_ID.toLowerCase()+".network") {
//        @Override
//        public Item getTabIconItem() {
//            return DataHandler.crushed_Selenium_Ore;
//        }
//    };
}