package com.minsin56.VillagersNoseMod.VillagerStuff.Professions;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;

public class ProfessionNoseDealer extends VillagerProfession
{
    public ProfessionNoseDealer(String nameIn, PointOfInterestType pointOfInterestIn, ImmutableSet<Item> specificItemsIn, ImmutableSet<Block> relatedWorldBlocksIn, SoundEvent soundIn)
    {
        super("Nose Dealer", pointOfInterestIn, specificItemsIn, relatedWorldBlocksIn, soundIn);
    }


}
