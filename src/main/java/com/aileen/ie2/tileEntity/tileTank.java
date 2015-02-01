package com.aileen.ie2.tileEntity;

import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by Aileen on 27.01.2015.
 */
public class tileTank extends TileEntity implements IFluidHandler, IPipeConnection {

    protected FluidStack fluid;
    protected int maxTankVol = Integer.MAX_VALUE;

    private int getFreeSpace() {
        return maxTankVol - fluid.amount;
    }

    public int getCurrentAmmount() {
        if (fluid != null) {
            return fluid.amount;
        }
        return 0;
    }

    public int getFluidID() {
        if (fluid != null) {
            return fluid.fluidID;
        }
        return 0;
    }

    public int getMaxAmmount() {
        return maxTankVol;
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (resource.equals(fluid)) {
            if (resource.amount < getFreeSpace()) {
                fluid.amount += resource.amount;
                return resource.amount;
            } else {
                fluid.amount += getFreeSpace();
                return getFreeSpace();
            }
        } else {
            if (resource.amount > maxTankVol) {
                fluid = resource;
                fluid.amount = maxTankVol;
                return maxTankVol;
            } else {
                fluid = resource;
                fluid.amount = resource.amount;
                return resource.amount;
            }
        }
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (fluid != null && resource.equals(fluid)) {
            if (fluid.amount >= resource.amount) {
                FluidStack output = new FluidStack(fluid, resource.amount);
                fluid.amount -= output.amount;
                return output;
            } else {
                FluidStack output = new FluidStack(fluid, fluid.amount);
                fluid = null;
                return output;
            }
        }
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (fluid != null) {
            if (fluid.amount >= maxDrain) {
                FluidStack output = new FluidStack(fluid, maxDrain);
                fluid.amount -= output.amount;
                return output;
            } else {
                FluidStack output = new FluidStack(fluid, fluid.amount);
                fluid = null;
                return output;
            }
        }
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[0];
    }

    @Override
    public ConnectOverride overridePipeConnection(IPipeTile.PipeType type, ForgeDirection with) {
        return ConnectOverride.CONNECT;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey("FluidName") && tag.hasKey("Amount")) {
            fluid = FluidRegistry.getFluidStack(tag.getString("FluidName"), tag.getInteger("Amount"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        if (fluid != null) {
            tag.setString("FluidName", FluidRegistry.getFluidName(fluid));
            tag.setInteger("Amount", fluid.amount);
        }
    }
}
