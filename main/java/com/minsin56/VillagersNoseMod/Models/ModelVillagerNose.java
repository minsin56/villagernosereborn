package com.minsin56.VillagersNoseMod.Models;

import com.minsin56.VillagersNoseMod.Items.ItemVillagerNose;
import net.minecraft.client.renderer.entity.model.BipedModel;

import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModelVillagerNose extends BipedModel<PlayerEntity>
{
    ModelRenderer Mdl;
    public ModelVillagerNose()
    {
        this(0.0f,0.0f,8,6);
    }

    private ModelVillagerNose(float Scale,float RotY,int TextureWidth,int TextureHeight)
    {
        super(Scale,RotY,0,0);
        this.bipedHead = new ModelRenderer(this);
        this.bipedHead.setTextureSize(8,6);
        bipedHead.addBox(-1.0F, -3f, -6.0F, 2, 4, 2, Scale);
        bipedHead.setRotationPoint(0.0f,RotY - 2.0f,0.0f);
    }

    @Override
    public void setRotationAngles(PlayerEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        if(ItemVillagerNose.EmeraldsAreNearby(entityIn.world,(int)entityIn.getPosX(),(int)entityIn.getPosY(),(int)entityIn.getPosZ(),10, ItemVillagerNose.DefaultSniffingBlocks))
        {
            bipedHead.rotateAngleY = MathHelper.cos((ageInTicks) * 0.2f * (float)(Math.PI));
        }
    }
}
