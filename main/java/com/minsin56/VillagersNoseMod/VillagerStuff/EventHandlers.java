package com.minsin56.VillagersNoseMod.VillagerStuff;

import com.minsin56.VillagersNoseMod.Networking.ClientPacket;
import com.minsin56.VillagersNoseMod.Networking.PacketHandler;
import com.minsin56.VillagersNoseMod.VillagerStuff.Nose.INose;
import com.minsin56.VillagersNoseMod.VillagerStuff.Nose.NoseProvider;
import com.minsin56.VillagersNoseMod.VillagersNoseReg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class EventHandlers
{
    @SubscribeEvent
    public static void AttachCaps(AttachCapabilitiesEvent<Entity> Event)
    {
        if(Event.getObject() instanceof VillagerEntity || Event.getObject() instanceof ZombieVillagerEntity)
        {
            Event.addCapability(NoseProvider.NoseIdent,new NoseProvider());
        }
    }

    @SubscribeEvent
    public static void NoseShearedEvent(PlayerInteractEvent.EntityInteract Event)
    {
        if(Event.getTarget() instanceof VillagerEntity || Event.getTarget() instanceof ZombieVillagerEntity)
        {
            Entity Villager = Event.getTarget();

            if(Event.getTarget().getCapability(NoseProvider.NoseCap).isPresent())
            {
                INose Cap = Villager.getCapability(NoseProvider.NoseCap).orElseThrow(NullPointerException::new);
                if(Cap.HasNose() && Event.getPlayer().getHeldItemMainhand().getItem() instanceof ShearsItem)
                {
                    System.out.println("HMMF");

                    Cap.SetHasNose(false);
                    Event.getTarget().playSound(SoundEvents.ENTITY_SHEEP_SHEAR,1.0f,1.0f);
                    Event.getTarget().playSound(SoundEvents.ENTITY_VILLAGER_HURT,1.0f,1.0f);

                    PacketDistributor.PacketTarget Dest = PacketDistributor.TRACKING_ENTITY.with(Event::getTarget);


                    if(Villager instanceof VillagerEntity)
                    {
                        Villager.entityDropItem(VillagersNoseReg.VillagerNoseReg.get());
                        //PacketHandler.Instance.send(Dest,new ClientPacket(Villager.getEntityId(),false));

                        Event.setCanceled(true);
                    }
                }

                if(Villager instanceof VillagerEntity && !Cap.HasNose())
                {
                    if(Event.getItemStack().getItem() != Items.VILLAGER_SPAWN_EGG && Villager.isAlive() && !((VillagerEntity) Villager).isSleeping() && !Event.getPlayer().isSneaking() && !((VillagerEntity) Villager).isChild())
                    {
                        Event.getPlayer().addStat(Stats.TALKED_TO_VILLAGER);
                        if(!((VillagerEntity) Villager).getOffers().isEmpty())
                        {
                            if(((VillagerEntity) Villager).getRevengeTarget() != Event.getPlayer())
                            {
                                ((VillagerEntity) Villager).setRevengeTarget(Event.getPlayer());
                            }
                            Event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }
}
