package com.minsin56.VillagersNoseMod.VillagerStuff.Entities;

import com.minsin56.VillagersNoseMod.VillagerStuff.TradeLists.TradeSuperNose;
import com.minsin56.VillagersNoseMod.VillagersNoseMod;
import com.minsin56.VillagersNoseMod.VillagersNoseReg;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.lang.reflect.Field;

public class EntityNoseDealer extends WanderingTraderEntity
{

    public EntityNoseDealer(EntityType<? extends WanderingTraderEntity> type, World worldIn)
    {
        super(type, worldIn);
    }


    @Override
    public void onAddedToWorld()
    {
        super.onAddedToWorld();
        populateTradeData();
        this.offers.clear();
        this.offers.add( new MerchantOffer
                (new ItemStack(VillagersNoseReg.VillagerNose,1),
                        new ItemStack(Items.EMERALD,18),
                        new ItemStack(VillagersNoseReg.SuperVillagerNose.get(),1),100,500,1));
    }



    @Override
    public void tick()
    {
        super.tick();

    }

    @Override
    protected void onVillagerTrade(MerchantOffer offer)
    {
        Minecraft.getInstance().world.playSound(getPosX(),getPosY(),getPosZ(), SoundEvents.ENTITY_VILLAGER_TRADE, SoundCategory.AMBIENT,1.0f,0.05f,false);
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer)
    {
        return false;
    }
}
