package com.minsin56.VillagersNoseMod.Items;

import com.minsin56.VillagersNoseMod.Models.ModelVillagerNose;
import com.minsin56.VillagersNoseMod.Structs.VillagerNoseDetection;
import com.minsin56.VillagersNoseMod.VillagersNoseReg;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.renderer.FaceDirection;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.extensions.IForgeItem;

import java.util.List;
import java.util.Random;

public class ItemVillagerNose extends ArmorItem implements IPlantable
{
    public int Range = 10;
    public BlockState PlantState()
    {
        return VillagersNoseReg.VillagerCrop.getDefaultState();
    }

    public static final Block[] DefaultSniffingBlocks = new Block[]{
            Blocks.EMERALD_ORE,
            Blocks.EMERALD_BLOCK
    };

    public Block[] GetSniffingBlocks()
    {
        return DefaultSniffingBlocks;
    }

    public static final SoundEvent[] VillagerSounds =
    {
            SoundEvents.ENTITY_VILLAGER_NO,
            SoundEvents.ENTITY_VILLAGER_YES,
            SoundEvents.ENTITY_VILLAGER_TRADE,
            SoundEvents.ENTITY_VILLAGER_AMBIENT
    };


    public ItemVillagerNose()
    {
        super(ArmorMaterial.LEATHER, EquipmentSlotType.HEAD,new Item.Properties().maxStackSize(64).rarity(Rarity.COMMON).group(ItemGroup.COMBAT));
    }


    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
    {
        return (A) new ModelVillagerNose();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
    {
        return "villagernose:textures/armor/nose.png";
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
        return PlantType.CROP;
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos)
    {
        return VillagersNoseReg.VillagerCrop.getDefaultState();
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context)
    {
        BlockState State = context.getWorld().getBlockState(context.getPos());
        Direction Dir = context.getFace();
        FaceDirection FaceDir = FaceDirection.getFacing(Dir);

        boolean PlayerCanInteractWithBlock = context.getPlayer().canPlayerEdit(context.getPos(), Dir, stack);
        boolean BlockCanSustainPlant = State.canSustainPlant(context.getWorld(), context.getPos(), Dir, this);
        boolean BlockOnTopIsAir = context.getWorld().getBlockState(context.getPos().add(0, 1, 0)).isAir(context.getWorld(), context.getPos());


        if(FaceDir == FaceDirection.UP && PlayerCanInteractWithBlock && BlockCanSustainPlant && BlockOnTopIsAir)
        {
            context.getWorld().setBlockState(context.getPos().add(0,1,0),PlantState());

            if(!context.getPlayer().isCreative())
            {
                stack.shrink(1);
            }

            return ActionResultType.SUCCESS;
        }

        return ActionResultType.FAIL;
    }


    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player)
    {
        if(player.ticksExisted % 20 == 0)
        {
            int Posx = (int)Math.floor(player.getPosX());
            int Posy = (int)Math.floor(player.getPosY());
            int Posz = (int)Math.floor(player.getPosZ());

            VillagerNoseDetection Dect = TryFindNearbyEmeralds(world,Posx,Posy,Posz,Range,GetSniffingBlocks());

            if(Dect != null)
            {
                Random Rand = new Random();

                double Distance = Dect.StartPosition.distanceSq(new Vector3i(Dect.DetectPosition.getX(),Dect.DetectPosition.getY(),Dect.DetectPosition.getZ()));

                player.playSound(GetVillagerSound(), SoundCategory.AMBIENT,(float)Range / (float)Distance, (Rand.nextFloat() - Rand.nextFloat()) * 0.2f + 1.0f);
            }
        }
    }

    public static VillagerNoseDetection TryFindNearbyEmeralds(World World, int PX ,int PY,int PZ, int Range, Block[] SniffingBlocks)
    {
        int Minx = PX - Range;
        int Miny = PY - Range;
        int MinZ = PZ - Range;
        int Maxx = PX + Range;
        int Maxy = PY + Range;
        int Maxz = PZ + Range;

        for(int x = Minx; x < Maxx;x++)
        {
            for(int y = Miny; y < Maxy;y++)
            {
                for(int z = MinZ; z < Maxz;z++)
                {
                    Block Block = World.getBlockState(new BlockPos(x,y,z)).getBlock();
                    for(int i = 0;i < SniffingBlocks.length;i++)
                    {
                        if(Block == SniffingBlocks[i])
                        {
                            VillagerNoseDetection Dect = new VillagerNoseDetection();
                            Dect.StartPosition = new BlockPos(PX,PY,PZ);
                            Dect.DetectPosition = new BlockPos(x,y,z);

                            return Dect;
                        }
                    }
                }
            }

        }
        return null;
    }

    public static boolean EmeraldsAreNearby(World World, int PX, int PY, int PZ,int Range,Block[] SniffingBlocks)
    {
        int Minx = PX - Range;
        int Miny = PY - Range;
        int MinZ = PZ - Range;
        int Maxx = PX + Range;
        int Maxy = PY + Range;
        int Maxz = PZ + Range;

        for(int x = Minx; x < Maxx;x++)
        {
            for(int y = Miny; y < Maxy;y++)
            {
                for(int z = MinZ; z < Maxz;z++)
                {
                    Block Block = World.getBlockState(new BlockPos(x,y,z)).getBlock();
                    for(int i = 0;i < SniffingBlocks.length;i++)
                    {
                        if(Block == SniffingBlocks[i])
                        {
                            return true;
                        }
                    }
                }
            }

        }
        return false;

    }

    private static SoundEvent GetVillagerSound()
    {
        return VillagerSounds[new Random().nextInt(VillagerSounds.length)];
    }
}
