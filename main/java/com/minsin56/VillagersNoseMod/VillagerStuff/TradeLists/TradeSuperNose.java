package com.minsin56.VillagersNoseMod.VillagerStuff.TradeLists;


import com.minsin56.VillagersNoseMod.VillagersNoseReg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;

import java.util.Random;

public class TradeSuperNose implements VillagerTrades.ITrade
{
    @Override
    public MerchantOffer getOffer(Entity trader, Random rand)
    {
        return  new MerchantOffer
                (new ItemStack(VillagersNoseReg.VillagerNose,1),
                        new ItemStack(Items.EMERALD_BLOCK,18),
                        new ItemStack(VillagersNoseReg.SuperVillagerNose.get(),1),100,500,1);
    }
}
