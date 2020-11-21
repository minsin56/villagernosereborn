package com.minsin56.VillagersNoseMod.Items;

import com.minsin56.VillagersNoseMod.Blocks.BlockZombieVillagerCrop;
import com.minsin56.VillagersNoseMod.VillagersNoseReg;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ItemZombieVillagerNose extends ItemVillagerNose
{
    public ItemZombieVillagerNose()
    {
        super();
        Range = 15;
    }

    @Override
    public BlockState PlantState()
    {
        return VillagersNoseReg.ZombieVillagerCrop.getDefaultState();
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos)
    {
        return new BlockZombieVillagerCrop().getDefaultState();
    }
}
