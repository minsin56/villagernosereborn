package com.minsin56.VillagersNoseMod.Items;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.nbt.CompoundNBT;

public class ItemSuperVillagerNose extends ItemVillagerNose
{
    public ItemSuperVillagerNose()
    {
        super();
        Range = 30;
    }



    @Override
    public IArmorMaterial getArmorMaterial()
    {
        return ArmorMaterial.DIAMOND;
    }


}
