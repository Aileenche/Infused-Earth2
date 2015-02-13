package com.aileen.ie2.api;

/**
 * Created by Aileen on 11.02.2015.
 */
public interface IEEnergyNet {
    public void setConPos(int x, int y, int z, int w);

    /*
    @Override
    public boolean hasValidController() {
        if (controller != null) {
            if(controller.posX == 0 && controller.posY == 0 && controller.posZ == 0){
                return false;
            }
            if (controller.posX != this.xCoord && controller.posY != this.yCoord && controller.posZ != this.zCoord){
                World world = MinecraftServer.getServer().worldServerForDimension(controller.worldId);
                TileEntity tileEntity = world.getTileEntity(controller.posX,controller.posY,controller.posZ);
                if(tileEntity != null && tileEntity instanceof tileController){
                    return true;
                }
            }
        }
        controller = new Coords(0, 0, 0, 0);
        return false;
    }
    */
    public boolean hasValidController();
}
