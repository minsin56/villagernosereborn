package com.minsin56.VillagersNoseMod.Blocks;

import com.minsin56.VillagersNoseMod.VillagersNoseReg;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockVillagerCrop extends CropsBlock
{
    EntityType VillagerToSpawn;

    public BlockVillagerCrop()
    {
        super(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP).hardnessAndResistance(0));
        VillagerToSpawn = EntityType.VILLAGER;
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state)
    {
        if(isMaxAge(state))
        {
            SpawnVillager((World)worldIn,pos);
        }
        else
        {
            ItemStack Stack = new ItemStack(VillagersNoseReg.VillagerNose,1);
            worldIn.addEntity(new ItemEntity((World)worldIn,pos.getX(),pos.getY(),pos.getZ(),Stack));
        }
        super.onPlayerDestroy(worldIn, pos, state);
    }

    @Override
    protected IItemProvider getSeedsItem()
    {
        return VillagersNoseReg.VillagerNose;
    }

    private void SpawnVillager(World world, BlockPos pos)
    {
        Entity VillagerEntity = VillagerToSpawn.create(world);
        if (VillagerEntity != null) {
            VillagerEntity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            VillagerEntity.recalculateSize();
            world.addEntity(VillagerEntity);
        }
    }

}
