package com.aileen.ie2.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public enum Particles {
    FLAMEBLUE, FLAMERED, FLAMEGREEN, FLAME, LIGHT;

    public void spawnParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null) {
            int particleSetting = mc.gameSettings.particleSetting;

            if (particleSetting == 2 || (particleSetting == 1 && world.rand.nextInt(3) == 0)) {
                return;
            }

            double distanceX = mc.renderViewEntity.posX - x;
            double distanceY = mc.renderViewEntity.posY - y;
            double distanceZ = mc.renderViewEntity.posZ - z;

            double maxDistance = 16;
            if (distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ > maxDistance * maxDistance) {
                return;
            }

            EntityFX particleEffect = null;
            switch (this) {
                case FLAME:
                    particleEffect = new EntityFlameFX(world, x, y, z, 0, 0, 0);
                    break;
                case FLAMERED:
                    particleEffect = new EntityLightFX(world, x, y, z, 0, 0, 0);
                    break;
                case FLAMEGREEN:
                    particleEffect = new EntityFlameGREENFx(world, x, y, z, 0, 0, 0);
                    break;
                case FLAMEBLUE:
                    particleEffect = new EntityFlameBLUEFx(world, x, y, z, 0, 0, 0);
                    break;
                case LIGHT:
                    particleEffect = new EntityLightFX(world, x, y, z, 0, 0, 0);
            }

            if (particleEffect != null) {
                Minecraft.getMinecraft().effectRenderer.addEffect(particleEffect);
            }


        }
    }

}
