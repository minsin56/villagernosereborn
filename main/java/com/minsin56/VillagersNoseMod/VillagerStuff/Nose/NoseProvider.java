package com.minsin56.VillagersNoseMod.VillagerStuff.Nose;

import com.minsin56.VillagersNoseMod.VillagersNoseMod;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class NoseProvider implements ICapabilitySerializable<INBT>
{
    public static final ResourceLocation NoseIdent = new ResourceLocation(VillagersNoseMod.ID,"cap_nose");

    @CapabilityInject(INose.class)
    public static Capability<INose> NoseCap = null;

    private INose Instance = NoseCap.getDefaultInstance();
    private final LazyOptional<INose> Holder = LazyOptional.of(() -> Instance);

    @Override
    public INBT serializeNBT()
    {
        return NoseCap.getStorage().writeNBT(NoseCap,Instance,null);
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        NoseCap.getStorage().readNBT(NoseCap,Instance,null,nbt);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
    {
        if(cap == NoseCap)
        {
            return Holder.cast();
        }

        return LazyOptional.empty();
    }
}
