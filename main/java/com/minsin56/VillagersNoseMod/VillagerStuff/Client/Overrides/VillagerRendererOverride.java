package com.minsin56.VillagersNoseMod.VillagerStuff.Client.Overrides;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.VillagerLevelPendantLayer;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;

public class VillagerRendererOverride extends MobRenderer<VillagerEntity,VillagerModelOverride<VillagerEntity>>
{
    private static final ResourceLocation field_217779_a = new ResourceLocation("textures/entity/villager/villager.png");

    public VillagerRendererOverride(EntityRendererManager rendererManager, IReloadableResourceManager resourceManager)
    {
        super(rendererManager, new VillagerModelOverride<>(0.0F), 0.5F);
        addLayer(new HeadLayer<>(this));
        addLayer(new VillagerLevelPendantLayer<>(this, resourceManager, "villager"));
    }


    public ResourceLocation getEntityTexture(VillagerEntity entity)
    {
        return field_217779_a;
    }
}
