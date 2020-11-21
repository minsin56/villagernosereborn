package com.minsin56.VillagersNoseMod.VillagerStuff.Client.Renderers;

import com.minsin56.VillagersNoseMod.VillagerStuff.Entities.EntityNoseDealer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.VillagerLevelPendantLayer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.util.ResourceLocation;

public class RendererNoseDealer extends MobRenderer<EntityNoseDealer,VillagerModel<EntityNoseDealer>>
{

    public RendererNoseDealer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new VillagerModel<>(0.0F), 0.5F);
    }



    protected void preRenderCallback(VillagerEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 0.9375F;
        if (entitylivingbaseIn.isChild()) {
            f = (float)((double)f * 0.5D);
            this.shadowSize = 0.25F;
        } else {
            this.shadowSize = 0.5F;
        }

        matrixStackIn.scale(f, f, f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityNoseDealer entity)
    {
        return new ResourceLocation("villagernose","textures/entity/nose_dealer.png");
    }
}

