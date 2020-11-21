package com.minsin56.VillagersNoseMod.Containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

public class OreNoseContainer extends Container
{
    public OreNoseContainer(ContainerType<OreNoseContainer> type, int id)
    {
        super(type, id);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }
}
