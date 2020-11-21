package com.minsin56.VillagersNoseMod.VillagerStuff.Nose;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class NoseStorage implements Capability.IStorage<INose>
{
    @Nullable
    @Override
    public INBT writeNBT(Capability<INose> capability, INose instance, Direction side)
    {
        CompoundNBT NBT = new CompoundNBT();
        NBT.putBoolean("HasNose",instance.HasNose());
        return NBT;
    }

    @Override
    public void readNBT(Capability<INose> capability, INose instance, Direction side, INBT nbt)
    {
        CompoundNBT Cyka = (CompoundNBT) nbt;
        instance.SetHasNose(Cyka.getBoolean("HasNose"));
    }
}
