package com.aileen.ie2.item.tools;

import com.aileen.ie2.api.IHarvesttool;
import com.aileen.ie2.creativeTab.CreativeTabIE2;
import com.aileen.ie2.handler.DataHandler;
import com.aileen.ie2.helper.LogHelper;
import com.aileen.ie2.helper.NBTHelper;
import com.aileen.ie2.libs.DigitalItemstack;
import com.aileen.ie2.libs.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Aileen on 04.02.2015.
 */
public class AxeIE extends ItemAxe implements IHarvesttool {
    public float efficiencyOnProperMaterial = 4.0F;
    public HashSet<Block> blackList = new HashSet<Block>();
    public float damageVsEntity;
    private String unlocalized_Name;
    private Block[] blocksEffectiveAgainst;
    private Material[] materialEffectiveAgainst;
    private IIcon broken;
    private IIcon charged;
    private int maxDamageEFF;
    private int storageMaxSize;

    public AxeIE(ToolMaterial material, String unlocalizedName) {
        super(material);
        this.setCreativeTab(CreativeTabIE2.blocks);
        this.unlocalized_Name = unlocalizedName;
        this.setUnlocalizedName(unlocalizedName);
        this.blocksEffectiveAgainst = new Block[]{Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.double_wooden_slab, Blocks.wooden_slab, Blocks.pumpkin, Blocks.lit_pumpkin};
        this.materialEffectiveAgainst = new Material[]{Material.leaves, Material.wood, Material.plants, Material.vine, Material.web};
        this.maxStackSize = 1;
        this.setMaxDamage(material.getMaxUses());
        this.damageVsEntity = 1.0F + material.getDamageVsEntity();
        this.maxDamageEFF = getMaxDamage() - 1;
        blackList.add(Blocks.water);
        blackList.add(Blocks.lava);
    }

    @Override
    public String getUnlocalizedName() {
        return this.unlocalized_Name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.unlocalized_Name);
        this.broken = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.unlocalized_Name + "_broken");
        this.charged = iconRegister.registerIcon(Reference.MOD_ID + ":" + this.unlocalized_Name + "_charged");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconIndex(ItemStack itemStack) {
        if (itemStack.getItemDamage() >= maxDamageEFF) {
            return this.broken;
        }
        if (NBTHelper.getBoolean(itemStack, "charge")) {
            return this.charged;
        }
        return this.itemIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack itemStack, int pass) {
        if (itemStack.getItemDamage() >= maxDamageEFF) {
            return this.broken;
        }
        if (NBTHelper.getBoolean(itemStack, "charge")) {
            return this.charged;
        }
        return this.itemIcon;
    }

    private boolean checkBlockEFF(Block block) {
        for (int i = 0; i < this.blocksEffectiveAgainst.length; ++i) {
            if (this.blocksEffectiveAgainst[i] == block) {
                return true;
            }
        }


        for (int i = 0; i < this.materialEffectiveAgainst.length; ++i) {
            if (this.materialEffectiveAgainst[i] == block.getMaterial()) {
                return true;
            }
        }
        if (block.getLocalizedName().equalsIgnoreCase("tile.null.name")) {
            return true;
        }
        return false;
    }

    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        createNBT(stack);
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null && nbt.hasKey("charge") && nbt.getBoolean("charge")) {
            World world = player.worldObj;
            Block wood = world.getBlock(x, y, z);
            if (stack.getItemDamage() < maxDamageEFF) {
                if (wood == null) {
                    return super.onBlockStartBreak(stack, x, y, z, player);
                }
                if ((wood.isWood(world, x, y, z)) || (wood.getMaterial() == Material.wood)) {
                    int height = y;
                    boolean foundTop = false;
                    do {
                        height++;
                        Block block = world.getBlock(x, height, z);
                        if (block == wood) {
                            continue;
                        }
                        height--;
                        foundTop = true;
                    }
                    while (!foundTop);
                    int numLeaves = 0;
                    Block leav;
                    if (height - y < 250) {
                        for (int xPos = x - 1; xPos <= x + 1; xPos++) {
                            for (int yPos = height - 1; yPos <= height + 1; yPos++) {
                                for (int zPos = z - 1; zPos <= z + 1; zPos++) {
                                    Block leaves = world.getBlock(xPos, yPos, zPos);
                                    if ((leaves != null) && (leaves.isLeaves(world, xPos, yPos, zPos))) {
                                        numLeaves++;
                                        leav = leaves;
                                    }
                                }
                            }
                        }
                    }
                    int meta = world.getBlockMetadata(x, y, z);
                    if (numLeaves > 3)
                    if (numLeaves > 3)
                        breakTree(world, x, y, z, stack, wood, meta, player);
                    else {
                        destroyWood(world, x, y, z, stack, player);
                    }
                    if (!world.isRemote) {
                        //world.playAuxSFX(2001, x, y, z, wood + (meta << 12));
                    }
                } else if (wood.getMaterial() == Material.wood) {
                    int meta = world.getBlockMetadata(x, y, z);
                    destroyWood(world, x, y, z, stack, player);
                    if (!world.isRemote) {
                        //world.playAuxSFX(2001, x, y, z, woodID + (meta << 12));
                    }
                }
            }
            return super.onBlockStartBreak(stack, x, y, z, player);

        }
        return false;
    }

    private void destroyWood(World world, int x, int y, int z, ItemStack stack, EntityPlayer player) {
        for (int xPos = x - 1; xPos <= x + 1; xPos++) {
            for (int yPos = y - 1; yPos <= y + 1; yPos++) {
                for (int zPos = z - 1; zPos <= z + 1; zPos++) {
                    Block block = world.getBlock(xPos, yPos, zPos);
                    int meta = world.getBlockMetadata(xPos, yPos, zPos);
                    if ((block == null) || (block.getMaterial() != Material.wood && block.getMaterial() != Material.leaves))
                        continue;
                    boolean cancelHarvest = false;
                    if (cancelHarvest)
                        continue;
                    world.setBlockToAir(xPos, yPos, zPos);
                    if (player.capabilities.isCreativeMode)
                        continue;
                    List<ItemStack> stacks = block.getDrops(world, x, y, z, meta, EnchantmentHelper.getFortuneModifier(player));
                    if (stacks != null) {
                        for (ItemStack s : stacks) {
                            if (s != null) {
                                boolean added = addItem(s, stack);
                                if (!added) {
                                    mineStack(world, s, player);
                                }
                            }
                        }
                    }
                    if (stack.getItemDamage() < maxDamageEFF) {
                        stack.damageItem(1, player);
                    }
                    world.setBlockToAir(xPos, yPos, zPos);
                }
            }
        }
    }

    private void breakTree(World world, int x, int y, int z, ItemStack stack, Block bID,  int meta, EntityPlayer player) {
        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt != null && nbt.hasKey("charge") && nbt.getBoolean("charge")) {
            for (int xPos = x - 1; xPos <= x + 1; xPos++) {
                for (int yPos = y; yPos <= y + 1; yPos++) {
                    for (int zPos = z - 1; zPos <= z + 1; zPos++) {
                        Block localblock = world.getBlock(xPos, yPos, zPos);
                        if (bID != localblock)
                            continue;
                        meta = world.getBlockMetadata(xPos, yPos, zPos);
                        boolean cancelHarvest = false;
                        if (cancelHarvest) {
                            breakTree(world, xPos, yPos, zPos, stack, bID, meta, player);
                        } else {
                            if ((localblock != bID) || (world.getBlockMetadata(xPos, yPos, zPos) % 4 != meta % 4)) {
                                continue;
                            }
                            if (!player.capabilities.isCreativeMode) {
                                if (localblock.removedByPlayer(world, player, xPos, yPos, zPos)) {
                                    localblock.onBlockDestroyedByPlayer(world, xPos, yPos, zPos, meta);
                                }
                                List<ItemStack> stacks = localblock.getDrops(world, x, y, z, meta, EnchantmentHelper.getFortuneModifier(player));
                                if (stacks != null) {
                                    for (ItemStack s : stacks) {
                                        if (s != null) {
                                            if (nbt.getBoolean("furnace")) {
                                                ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(s);
                                                LogHelper.debug(result.getDisplayName()+" -> "+result.stackSize);
                                                if (result != null) {
                                                    if (nbt.getBoolean("drop")) {
                                                        boolean added = addItem(result, stack);
                                                        if (!added) {
                                                            boolean added2 = addItem(s, stack);
                                                            if (!added2) {
                                                                mineStack(world, s, player);
                                                            }
                                                        }
                                                    } else {
                                                        mineStack(world, result, player);
                                                    }
                                                } else {
                                                    if (nbt.getBoolean("drop")) {
                                                        boolean added = addItem(s, stack);
                                                        if (!added) {
                                                            mineStack(world, s, player);
                                                        }
                                                    } else {
                                                        mineStack(world, result, player);
                                                    }
                                                }
                                            } else {
                                                if (nbt.getBoolean("drop")) {
                                                    boolean added = addItem(s, stack);
                                                    if (!added) {
                                                        mineStack(world, s, player);
                                                    }
                                                } else {
                                                    mineStack(world, s, player);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (stack.getItemDamage() < maxDamageEFF) {
                                    stack.damageItem(1, player);
                                }
                                world.setBlockToAir(xPos, yPos, zPos);
                                onBlockDestroyed(stack, world, localblock, xPos, yPos, zPos, player);
                            } else {
                                world.setBlockToAir(xPos, yPos, zPos);
                            }
                            breakTree(world, xPos, yPos, zPos, stack, bID, meta, player);
                        }
                    }
                }
            }
        }
    }

    public boolean addItem(ItemStack itemToAdd, ItemStack tool) {
        if (tool.hasTagCompound() && tool.getTagCompound().getCompoundTag("Tool") != null) {
            NBTTagCompound nbt = tool.getTagCompound();
            boolean charge = nbt.getBoolean("charge");
            boolean furnace = nbt.getBoolean("furnace");
            boolean drop = nbt.getBoolean("drop");
            int category = nbt.getInteger("category");
            NBTTagList items = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
            tool.getTagCompound().removeTag("Items");
            DigitalItemstack[] storedItems = new DigitalItemstack[27];
            if (this.toolMaterial == DataHandler.TOPAS) {
                storageMaxSize = (512);
            }
            if (this.toolMaterial == DataHandler.AMETHYST) {
                storageMaxSize = (5000);
            }
            if (this.toolMaterial == DataHandler.SAPPHIRE) {
                storageMaxSize = (50000);
            }
            if (this.toolMaterial == DataHandler.RUBY) {
                storageMaxSize = (100000);
            }
            if (this.toolMaterial == DataHandler.SELENIUM) {
                storageMaxSize = (1000000);
            }
            if (this.toolMaterial == DataHandler.TITANIUM) {
                storageMaxSize = (10000000);
            }
            for (int i = 0; i < items.tagCount(); i++) {
                NBTTagCompound slot = items.getCompoundTagAt(i);
                byte slotID = slot.getByte("SlotID");
                storedItems[slotID] = DigitalItemstack.loadItemStackFromNBT(slot);
            }
            int matchingSlot = -1;
            for (int i = 0; i < storedItems.length; i++) {
                if (storedItems[i] != null && storedItems[i].getItem() == itemToAdd.getItem() && storedItems[i].getItemDamage() == itemToAdd.getItemDamage()) {
                    if (storedItems[i].stackSize < storageMaxSize) {
                        matchingSlot = i;
                        break;
                    }
                }
            }

            boolean worked = false;
            if (matchingSlot != -1 && storedItems[matchingSlot].stackSize + itemToAdd.stackSize <= storedItems[matchingSlot].getMaxStackSize()) {
                storedItems[matchingSlot].stackSize += itemToAdd.stackSize;
                worked = true;
            } else {
                int nextFreeSlot = -1;
                for (int i = 0; i < storedItems.length; i++) {
                    if (storedItems[i] == null) {
                        nextFreeSlot = i;
                        break;
                    }
                }
                if (nextFreeSlot != -1) {
                    DigitalItemstack stack = new DigitalItemstack(itemToAdd.getItem(), itemToAdd.stackSize, itemToAdd.getItemDamage());//.toDigitalItemStack(itemToAdd, itemToAdd.stackSize);
                    if (itemToAdd.hasTagCompound()) {
                        stack.setTagCompound(itemToAdd.getTagCompound());
                    }
                    storedItems[nextFreeSlot] = stack.copy();
                    worked = true;
                }
            }
            NBTTagList newList = new NBTTagList();
            for (int i = 0; i < storedItems.length; i++) {
                if (storedItems[i] != null) {
                    NBTTagCompound cuSlot = new NBTTagCompound();
                    cuSlot.setByte("SlotID", (byte) i);
                    storedItems[i].writeToNBT(cuSlot);
                    newList.appendTag(cuSlot);
                }
            }
            for (int i = 0; i < storedItems.length; i++) {
                if (storedItems[i] != null) {
                    LogHelper.debug("#" + i + " -> " + storedItems[i].getDisplayName() + " | " + storedItems[i].stackSize);
                }
            }
            NBTTagCompound newNBT = new NBTTagCompound();
            newNBT.setTag("Tool", new NBTTagCompound());
            newNBT.setBoolean("charge", charge);
            newNBT.setBoolean("furnace", furnace);
            newNBT.setBoolean("drop", drop);
            newNBT.setInteger("category", category);
            newNBT.setTag("Items", newList);
            tool.setTagCompound(newNBT);
            return worked;
        }
        return false;
    }

    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        if (par1ItemStack.getItemDamage() >= maxDamageEFF) {
            return 0.0F;
        }
        for (int i = 0; i < this.blocksEffectiveAgainst.length; ++i) {
            if (this.blocksEffectiveAgainst[i] == par2Block) {
                return this.efficiencyOnProperMaterial;
            }
        }
        for (int i = 0; i < this.materialEffectiveAgainst.length; ++i) {
            if (this.materialEffectiveAgainst[i] == par2Block.getMaterial()) {
                return this.efficiencyOnProperMaterial;
            }
        }
        if (par2Block.getLocalizedName().equalsIgnoreCase("tile.null.name")) {
            return this.efficiencyOnProperMaterial;
        }
        return 1.0F;
    }

    public boolean canHarvestBlock(Block par1Block) {
        return par1Block == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() >= 3 : (par1Block != Blocks.diamond_block && par1Block != Blocks.diamond_ore ? (par1Block != Blocks.emerald_ore && par1Block != Blocks.emerald_block ? (par1Block != Blocks.gold_block && par1Block != Blocks.gold_ore ? (par1Block != Blocks.iron_block && par1Block != Blocks.iron_ore ? (par1Block != Blocks.lapis_block && par1Block != Blocks.lapis_ore ? (par1Block != Blocks.redstone_ore && par1Block != Blocks.lit_redstone_ore ? (par1Block.getMaterial() == Material.rock ? true : (par1Block.getMaterial() == Material.iron ? true : par1Block.getMaterial() == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }

    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
        if (par1ItemStack.getItemDamage() >= maxDamageEFF) {
            return false;
        }
        par1ItemStack.damageItem(2, par3EntityLivingBase);
        return true;
    }

    private void mineStack(World world, ItemStack stack, EntityPlayer player) {
        if (!world.isRemote) {
            if (stack.stackSize > 0) {
                float f = world.rand.nextFloat() * 0.8F + 0.1F;
                float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityitem = new EntityItem(world, player.posX + f, player.posY + f1 + 0.5F, player.posZ + f2, stack);
                entityitem.lifespan = 1200;
                entityitem.delayBeforeCanPickup = 1;
                float f3 = 0.05F;
                entityitem.motionX = (float) world.rand.nextGaussian() * f3;
                entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 0.1F;
                entityitem.motionZ = (float) world.rand.nextGaussian() * f3;
                world.spawnEntityInWorld(entityitem);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List info, boolean useExtraInformation) {
        if (NBTHelper.hasTag(itemstack, "charge")) {
            boolean mode = NBTHelper.getBoolean(itemstack, "charge");
            if (mode) {
                info.add(EnumChatFormatting.GREEN + "Supercharge active!");
                info.add(EnumChatFormatting.GREEN + "Uses Left: ~" + ((itemstack.getMaxDamage() - itemstack.getItemDamage()) / 27));
            } else {
                info.add(EnumChatFormatting.RED + "Supercharge inactive!");
                info.add(EnumChatFormatting.RED + "Uses Left: " + (itemstack.getMaxDamage() - itemstack.getItemDamage()));
            }
            boolean furnace = NBTHelper.getBoolean(itemstack, "furnace");
            if (furnace) {
                info.add(EnumChatFormatting.GREEN + "FurnaceMode active!");
            } else {
                info.add(EnumChatFormatting.RED + "FurnaceMode inactive!");
            }
            boolean drop = NBTHelper.getBoolean(itemstack, "drop");
            if (drop) {
                info.add(EnumChatFormatting.GREEN + "Storage Mode: Internal!");
            } else {
                info.add(EnumChatFormatting.RED + "Storage Mode: Drop!");
            }

            NBTTagList items = itemstack.stackTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
            DigitalItemstack[] storedItems = new DigitalItemstack[27];
            for (int i = 0; i < items.tagCount(); i++) {
                NBTTagCompound slot = items.getCompoundTagAt(i);
                byte slotID = slot.getByte("SlotID");
                storedItems[slotID] = DigitalItemstack.loadItemStackFromNBT(slot);
            }
            for (int i = 0; i < storedItems.length; i++) {
                if (storedItems[i] != null) {
                    info.add(storedItems[i].getDisplayName() + "(" + storedItems[i].stackSize + ")");
                }
            }
        }
    }

    public void createNBT(ItemStack tool) {
        if (!tool.hasTagCompound() || tool.getTagCompound().getCompoundTag("Tool") == null) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("Tool", new NBTTagCompound());
            nbt.getCompoundTag("Tool").setString("type", "pickaxe");
            nbt.setBoolean("charge", false);
            nbt.setBoolean("furnace", false);
            nbt.setBoolean("drop", false);
            nbt.setInteger("category", 0);
            NBTTagList list = new NBTTagList();
            nbt.setTag("Items", list);
            tool.setTagCompound(nbt);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!world.isRemote) {
            createNBT(itemStack);
            NBTTagCompound nbt = itemStack.getTagCompound();
            if (!entityPlayer.isSneaking()) {
                int mode = nbt.getInteger("category");
                if (mode == 0) {
                    boolean charge = nbt.getBoolean("charge");
                    if (charge) {
                        nbt.setBoolean("charge", false);
                        entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Supercharge disengaged!"));
                    } else {
                        nbt.setBoolean("charge", true);
                        entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Supercharge engaged!"));
                    }
                } else if (mode == 1) {
                    boolean furnace = nbt.getBoolean("furnace");
                    if (furnace) {
                        nbt.setBoolean("furnace", false);
                        entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "FurnaceMode disengaged!"));
                    } else {
                        nbt.setBoolean("furnace", true);
                        entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "FurnaceMode engaged!"));
                    }
                } else if (mode == 2) {
                    boolean drop = nbt.getBoolean("drop");
                    if (drop) {
                        nbt.setBoolean("drop", false);
                        entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Storage Mode is set to Drop!"));
                    } else {
                        nbt.setBoolean("drop", true);
                        entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Storage Mode is set to Internal Storage!"));
                    }
                } else {
                    entityPlayer.addChatMessage(new ChatComponentText("hier lief was schief...."));
                }
            } else {
                int mode = nbt.getInteger("category");
                if (mode == 0) {
                    nbt.setInteger("category", 1);
                    entityPlayer.addChatMessage(new ChatComponentText("Category: FurnaceMode"));
                } else if (mode == 1) {
                    nbt.setInteger("category", 2);
                    entityPlayer.addChatMessage(new ChatComponentText("Category: Storage Mode"));
                } else if (mode == 2) {
                    nbt.setInteger("category", 0);
                    entityPlayer.addChatMessage(new ChatComponentText("Category: Supercharge"));
                } else {
                    entityPlayer.addChatMessage(new ChatComponentText("hier lief was schief...."));
                }
            }
            itemStack.setTagCompound(nbt);
        }
        return itemStack;
    }

    @Override
    public DigitalItemstack[] iInvToEx(ItemStack itemstack) {
        createNBT(itemstack);
        NBTTagList items = itemstack.stackTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        DigitalItemstack[] storedItems = new DigitalItemstack[27];
        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound slot = items.getCompoundTagAt(i);
            byte slotID = slot.getByte("SlotID");
            storedItems[slotID] = DigitalItemstack.loadItemStackFromNBT(slot);
        }
        return storedItems;
    }

    @Override
    public boolean exInvToI(ItemStack item, DigitalItemstack[] digitalItemstack) {
        if (item.hasTagCompound() && item.getTagCompound().getCompoundTag("Tool") != null) {
            NBTTagCompound nbt = item.getTagCompound();
            boolean charge = nbt.getBoolean("charge");
            boolean furnace = nbt.getBoolean("furnace");
            boolean drop = nbt.getBoolean("drop");
            int category = nbt.getInteger("category");
            item.getTagCompound().removeTag("Items");
            NBTTagList newList = new NBTTagList();
            for (int i = 0; i < digitalItemstack.length; i++) {
                if (digitalItemstack[i] != null) {
                    NBTTagCompound cuSlot = new NBTTagCompound();
                    cuSlot.setByte("SlotID", (byte) i);
                    digitalItemstack[i].writeToNBT(cuSlot);
                    newList.appendTag(cuSlot);
                }
            }
            NBTTagCompound newNBT = new NBTTagCompound();
            newNBT.setTag("Tool", new NBTTagCompound());
            newNBT.setBoolean("charge", charge);
            newNBT.setBoolean("furnace", furnace);
            newNBT.setBoolean("drop", drop);
            newNBT.setInteger("category", category);
            newNBT.setTag("Items", newList);
            item.setTagCompound(newNBT);
            return true;
        }
        return false;
    }

    @Override
    public DigitalItemstack getItemstackInSlot(ItemStack item, int Slot) {
        createNBT(item);
        NBTTagList items = item.stackTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        DigitalItemstack[] storedItems = new DigitalItemstack[27];
        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound slot = items.getCompoundTagAt(i);
            byte slotID = slot.getByte("SlotID");
            storedItems[slotID] = DigitalItemstack.loadItemStackFromNBT(slot);
        }
        if (storedItems[Slot] != null) {
            return storedItems[Slot];
        }
        return null;
    }

    @Override
    public boolean setItemstackInSlot(ItemStack item, int Slot, DigitalItemstack digitalItemstack) {
        if (item.hasTagCompound() && item.getTagCompound().getCompoundTag("Tool") != null) {
            NBTTagCompound nbt = item.getTagCompound();
            boolean charge = nbt.getBoolean("charge");
            boolean furnace = nbt.getBoolean("furnace");
            boolean drop = nbt.getBoolean("drop");
            int category = nbt.getInteger("category");
            NBTTagList items = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
            item.getTagCompound().removeTag("Items");
            DigitalItemstack[] storedItems = new DigitalItemstack[27];
            for (int i = 0; i < items.tagCount(); i++) {
                NBTTagCompound slot = items.getCompoundTagAt(i);
                byte slotID = slot.getByte("SlotID");
                storedItems[slotID] = DigitalItemstack.loadItemStackFromNBT(slot);
            }

            NBTTagList newList = new NBTTagList();
            for (int i = 0; i < storedItems.length; i++) {
                if (storedItems[i] != null) {
                    NBTTagCompound cuSlot = new NBTTagCompound();
                    cuSlot.setByte("SlotID", (byte) i);
                    if (i == Slot) {
                        digitalItemstack.writeToNBT(cuSlot);
                    } else {
                        storedItems[i].writeToNBT(cuSlot);
                    }
                    newList.appendTag(cuSlot);
                }
            }

            NBTTagCompound newNBT = new NBTTagCompound();
            newNBT.setTag("Tool", new NBTTagCompound());
            newNBT.setBoolean("charge", charge);
            newNBT.setBoolean("furnace", furnace);
            newNBT.setBoolean("drop", drop);
            newNBT.setInteger("category", category);
            newNBT.setTag("Items", newList);
            item.setTagCompound(newNBT);
            return true;
        }
        return false;
    }

    @Override
    public boolean clearItemstackInSlot(ItemStack item, int Slot) {
        if (item.hasTagCompound() && item.getTagCompound().getCompoundTag("Tool") != null) {
            NBTTagCompound nbt = item.getTagCompound();
            boolean charge = nbt.getBoolean("charge");
            boolean furnace = nbt.getBoolean("furnace");
            boolean drop = nbt.getBoolean("drop");
            int category = nbt.getInteger("category");
            NBTTagList items = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
            item.getTagCompound().removeTag("Items");
            DigitalItemstack[] storedItems = new DigitalItemstack[27];
            for (int i = 0; i < items.tagCount(); i++) {
                NBTTagCompound slot = items.getCompoundTagAt(i);
                byte slotID = slot.getByte("SlotID");
                storedItems[slotID] = DigitalItemstack.loadItemStackFromNBT(slot);
            }

            NBTTagList newList = new NBTTagList();
            for (int i = 0; i < storedItems.length; i++) {
                if (storedItems[i] != null) {
                    NBTTagCompound cuSlot = new NBTTagCompound();
                    cuSlot.setByte("SlotID", (byte) i);
                    if (i != Slot) {
                        storedItems[i].writeToNBT(cuSlot);
                    }
                    newList.appendTag(cuSlot);
                }
            }

            NBTTagCompound newNBT = new NBTTagCompound();
            newNBT.setTag("Tool", new NBTTagCompound());
            newNBT.setBoolean("charge", charge);
            newNBT.setBoolean("furnace", furnace);
            newNBT.setBoolean("drop", drop);
            newNBT.setInteger("category", category);
            newNBT.setTag("Items", newList);
            item.setTagCompound(newNBT);
            return true;
        }
        return false;
    }
}
