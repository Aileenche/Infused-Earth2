package com.aileen.ie2.libs;

import com.aileen.ie2.inventory.ITransactor;
import com.aileen.ie2.inventory.Transactor;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.*;

/**
 * Created by Aileen on 18.01.14.
 */
public class Utils {

    private static final List<ForgeDirection> directions = new ArrayList(Arrays.asList(ForgeDirection.VALID_DIRECTIONS));

    private static Random rand = new Random();

    public static int addToRandomInventoryAround(World world, int x, int y, int z, ItemStack stack) {
        Collections.shuffle(directions);
        for (ForgeDirection orientation : directions) {
            BlockPosition pos = new BlockPosition(x, y, z, orientation);
            pos.moveForwards(1);

            TileEntity tileInventory = world.getTileEntity((int) pos.x, (int) pos.y, (int) pos.z);
            ITransactor transactor = Transactor.getTransactorFor(tileInventory);
            if ((transactor != null) && (transactor.add(stack, orientation.getOpposite(), false).stackSize > 0)) {
                return transactor.add(stack, orientation.getOpposite(), true).stackSize;
            }
        }
        return 0;
    }

    public static IInventory getInventory(IInventory inv) {
        if (inv instanceof TileEntityChest) {
            TileEntityChest chest = (TileEntityChest) inv;

            TileEntityChest adjacent = null;

            if (chest.adjacentChestXNeg != null) {
                adjacent = chest.adjacentChestXNeg;
            }

            if (chest.adjacentChestXPos != null) {
                adjacent = chest.adjacentChestXPos;
            }

            if (chest.adjacentChestZNeg != null) {
                adjacent = chest.adjacentChestZNeg;
            }

            if (chest.adjacentChestZPos != null) {
                adjacent = chest.adjacentChestZPos;
            }

            if (adjacent != null) {
                return new InventoryLargeChest("", inv, adjacent);
            }
            return inv;
        }
        return inv;
    }


}
