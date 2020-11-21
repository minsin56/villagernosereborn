package com.minsin56.VillagersNoseMod;

import com.minsin56.VillagersNoseMod.Blocks.BlockVillagerCrop;
import com.minsin56.VillagersNoseMod.Blocks.BlockZombieVillagerCrop;
import com.minsin56.VillagersNoseMod.Containers.OreNoseContainer;
import com.minsin56.VillagersNoseMod.GUIScreens.ScreenOreNose;
import com.minsin56.VillagersNoseMod.Items.ItemOreNose;
import com.minsin56.VillagersNoseMod.Items.ItemSuperVillagerNose;
import com.minsin56.VillagersNoseMod.Items.ItemVillagerNose;
import com.minsin56.VillagersNoseMod.Items.ItemZombieVillagerNose;
import com.minsin56.VillagersNoseMod.VillagerStuff.Entities.EntityNoseDealer;
import com.minsin56.VillagersNoseMod.VillagerStuff.Professions.ProfessionNoseDealer;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.rmi.registry.Registry;

public class VillagersNoseReg
{
    public static DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, VillagersNoseMod.ID);
    public static DeferredRegister<Block> Block = DeferredRegister.create(ForgeRegistries.BLOCKS, VillagersNoseMod.ID);
    public static DeferredRegister<EntityType<?>> Entity = DeferredRegister.create(ForgeRegistries.ENTITIES, VillagersNoseMod.ID);
    public static DeferredRegister<VillagerProfession> Professions = DeferredRegister.create(ForgeRegistries.PROFESSIONS,VillagersNoseMod.ID);

    public static final ItemVillagerNose VillagerNose = new ItemVillagerNose();
    public static final BlockVillagerCrop VillagerCrop = new BlockVillagerCrop();
    public static final BlockZombieVillagerCrop ZombieVillagerCrop = new BlockZombieVillagerCrop();

    public static final RegistryObject<EntityType<EntityNoseDealer>> NoseDealer = Entity.register("nose_dealer",() -> EntityType.Builder.create(EntityNoseDealer::new, EntityClassification.CREATURE).build("nose_dealer"));

    public  static RegistryObject<BlockVillagerCrop> VillagerCropReg;
    public  static RegistryObject<BlockZombieVillagerCrop> ZombieVillagerCropReg;
    public static final RegistryObject<ItemSuperVillagerNose> SuperVillagerNose = Items.register("super_villager_nose", ItemSuperVillagerNose::new);
    public static final RegistryObject<ItemOreNose> OreNose = Items.register("ore_villager_nose",ItemOreNose::new);

    public static RegistryObject<ItemVillagerNose> VillagerNoseReg;
    public static RegistryObject<ItemZombieVillagerNose> ZombieVillagerNoseReg;

    //public static RegistryObject<ContainerType<OreNoseContainer>> COreNoseContainer = Containers.register("ore_nose_cont",ContainerType.IFactory<OreNoseContainer>().);

    public static void Init()
    {
        VillagerNoseReg = Items.register("villager_nose",() -> VillagerNose);


        //COreNoseContainer = ScreenManager.registerFactory();

        //ZombieVillagerNoseReg = Items.register("zombie_villager_nose", ItemZombieVillagerNose::new);
        VillagerCropReg = Block.register("villager_crop", () -> VillagerCrop);
        //ZombieVillagerCropReg = Block.register("zombie_villager_crop", () -> ZombieVillagerCrop);

        Items.register(FMLJavaModLoadingContext.get().getModEventBus());
        Block.register(FMLJavaModLoadingContext.get().getModEventBus());
        Entity.register(FMLJavaModLoadingContext.get().getModEventBus());

    }
}
