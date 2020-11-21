package com.minsin56.VillagersNoseMod;

import com.minsin56.VillagersNoseMod.Networking.PacketHandler;
import com.minsin56.VillagersNoseMod.VillagerStuff.Client.Overrides.VillagerRendererOverride;
import com.minsin56.VillagersNoseMod.VillagerStuff.Client.Renderers.RendererNoseDealer;
import com.minsin56.VillagersNoseMod.VillagerStuff.Entities.EntityNoseDealer;
import com.minsin56.VillagersNoseMod.VillagerStuff.EventHandlers;
import com.minsin56.VillagersNoseMod.VillagerStuff.Nose.INose;
import com.minsin56.VillagersNoseMod.VillagerStuff.Nose.Nose;
import com.minsin56.VillagersNoseMod.VillagerStuff.Nose.NoseProvider;
import com.minsin56.VillagersNoseMod.VillagerStuff.Nose.NoseStorage;
import com.minsin56.VillagersNoseMod.VillagersNoseReg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ShearsItem;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod("villagernose")
public class VillagersNoseMod
{
    public static final String ID = "villagernose";
    public static final Logger Debuger = LogManager.getLogger();

    public VillagersNoseMod()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::CommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::EnqueueIMC);
        VillagersNoseReg.Init();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(EventHandlers.class);

    }

    private void CommonSetup(final FMLCommonSetupEvent Event)
    {
        CapabilityManager.INSTANCE.register(INose.class,new NoseStorage(), Nose::new);
        PacketHandler.Register();
        DeferredWorkQueue.runLater(() ->
        {
            GlobalEntityTypeAttributes.put(VillagersNoseReg.NoseDealer.get(),VillagerEntity.registerAttributes().create());
        });
    }

    private void ClientSetup(final FMLClientSetupEvent Event)
    {
        Minecraft mc = Minecraft.getInstance();
        IReloadableResourceManager resourceManager = (IReloadableResourceManager) mc.getResourceManager();
        EntityRendererManager re = mc.getRenderManager();
        re.register(EntityType.VILLAGER,new VillagerRendererOverride(re,resourceManager));
        RenderingRegistry.registerEntityRenderingHandler(VillagersNoseReg.NoseDealer.get(), RendererNoseDealer::new);
    }

    private void EnqueueIMC(final InterModEnqueueEvent Event)
    {

    }

    private void ProcessIMC(final InterModProcessEvent Event)
    {
        Debuger.info("Got IMC {}",Event.getIMCStream().map(M->M.getMessageSupplier().get()).collect(Collectors.toList()));
    }
}
