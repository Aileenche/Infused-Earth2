package com.aileen.ie2.block.ores;

import com.aileen.ie2.block.BlockIE;
import com.aileen.ie2.handler.DataHandler;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Aileen on 07.02.2015.
 */
public class GemIE extends BlockIE {
    String unlocalizedName;

    public GemIE(String unlocalized_Name) {
        super(unlocalized_Name);
        this.unlocalizedName = unlocalized_Name;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        if (this.unlocalizedName == "block_Ruby_Ore") {
            return DataHandler.item_gemRuby;
        }
        if (this.unlocalizedName == "block_Sapphire_Ore") {
            return DataHandler.item_gemSapphire;
        }
        if (this.unlocalizedName == "block_Amethyst_Ore") {
            return DataHandler.item_gemAmethyst;
        }
        if (this.unlocalizedName == "block_Topas_Ore") {
            return DataHandler.item_gemTopas;
        }
        return Item.getItemFromBlock(this);
    }
}
