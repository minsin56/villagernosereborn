package com.minsin56.VillagersNoseMod.VillagerStuff.Client.Overrides;

import com.minsin56.VillagersNoseMod.VillagerStuff.Nose.*;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.IHeadToggle;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import static com.minsin56.VillagersNoseMod.VillagerStuff.Nose.NoseProvider.NoseCap;


// mostly "borrowed" from minecraft
@OnlyIn(Dist.CLIENT)
public class VillagerModelOverride<T extends Entity> extends EntityModel<T> implements IHasHead, IHeadToggle {
    private final ModelRenderer villagerHead;
    private ModelRenderer villagerHeadwear;
    private final ModelRenderer villagerHeadwearAccessory;
    private final ModelRenderer villagerBody;
    private final ModelRenderer villagerArms;
    private final ModelRenderer rightVillagerLeg;
    private final ModelRenderer leftVillagerLeg;
    private final ModelRenderer villagerNose;

    VillagerModelOverride(float scale) {
        this(scale, 64, 64);
    }

    private VillagerModelOverride(float p_i51059_1_, int p_i51059_2_, int p_i51059_3_)
    {
        // float f = 0.5F;
        villagerHead = (new ModelRenderer(this)).setTextureSize(p_i51059_2_, p_i51059_3_);
        villagerHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        villagerHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i51059_1_);
        villagerHeadwear = (new ModelRenderer(this)).setTextureSize(p_i51059_2_, p_i51059_3_);
        villagerHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        villagerHeadwear.setTextureOffset(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i51059_1_ + 0.5F);
        villagerHead.addChild(villagerHeadwear);
        villagerHeadwearAccessory = (new ModelRenderer(this)).setTextureSize(p_i51059_2_, p_i51059_3_);
        villagerHeadwearAccessory.setRotationPoint(0.0F, 0.0F, 0.0F);
        villagerHeadwearAccessory.setTextureOffset(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16, 16, 1, p_i51059_1_);
        villagerHeadwearAccessory.rotateAngleX = (-(float) Math.PI / 2F);
        villagerHeadwear.addChild(villagerHeadwearAccessory);
        villagerNose = (new ModelRenderer(this)).setTextureSize(p_i51059_2_, p_i51059_3_);
        villagerNose.setRotationPoint(0.0F, -2.0F, 0.0F);
        villagerNose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, p_i51059_1_);
        villagerHead.addChild(villagerNose);
        villagerBody = (new ModelRenderer(this)).setTextureSize(p_i51059_2_, p_i51059_3_);
        villagerBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        villagerBody.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, p_i51059_1_);
        ModelRenderer villagerBodyAccessory = (new ModelRenderer(this)).setTextureSize(p_i51059_2_, p_i51059_3_);
        villagerBodyAccessory.setRotationPoint(0.0F, 0.0F, 0.0F);
        villagerBodyAccessory.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, p_i51059_1_ + 0.5F);
        villagerBody.addChild(villagerBodyAccessory);
        villagerArms = (new ModelRenderer(this)).setTextureSize(p_i51059_2_, p_i51059_3_);
        villagerArms.setRotationPoint(0.0F, 2.0F, 0.0F);
        villagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, p_i51059_1_);
        villagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, p_i51059_1_, true);
        villagerArms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, p_i51059_1_);
        rightVillagerLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(p_i51059_2_, p_i51059_3_);
        rightVillagerLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        rightVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i51059_1_);
        leftVillagerLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(p_i51059_2_, p_i51059_3_);
        leftVillagerLeg.mirror = true;
        leftVillagerLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        leftVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i51059_1_);
    }

    public void func_217146_a(boolean p_217146_1_) {
        villagerHead.showModel = p_217146_1_;
        villagerHeadwear.showModel = p_217146_1_;
        villagerHeadwearAccessory.showModel = p_217146_1_;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        boolean flag = false;
        if (entityIn instanceof AbstractVillagerEntity) {
            flag = ((AbstractVillagerEntity) entityIn).getShakeHeadTicks() > 0;
        }

        if(entityIn.getCapability(NoseCap).isPresent() && entityIn.getCapability(NoseCap).orElseGet(Nose::new).HasNose())
        {
            villagerNose.rotateAngleX = 0;
        }
        else{
            villagerNose.rotateAngleX = 130;
        }

        villagerHead.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        villagerHead.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        if (flag) {
            villagerHead.rotateAngleZ = 0.3F * MathHelper.sin(0.45F * ageInTicks);
            villagerHead.rotateAngleX = 0.4F;
        } else {
            villagerHead.rotateAngleZ = 0.0F;
        }

        villagerArms.rotationPointY = 3.0F;
        villagerArms.rotationPointZ = -1.0F;
        villagerArms.rotateAngleX = -0.75F;
        rightVillagerLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        leftVillagerLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        rightVillagerLeg.rotateAngleY = 0.0F;
        leftVillagerLeg.rotateAngleY = 0.0F;
    }

    @Override
    public ModelRenderer getModelHead() {
        return villagerHead;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
    {

        villagerHead.render(matrixStackIn,bufferIn,packedLightIn,packedOverlayIn);
        villagerBody.render(matrixStackIn,bufferIn,packedLightIn,packedOverlayIn);
        rightVillagerLeg.render(matrixStackIn,bufferIn,packedLightIn,packedOverlayIn);
        leftVillagerLeg.render(matrixStackIn,bufferIn,packedLightIn,packedOverlayIn);
        villagerArms.render(matrixStackIn,bufferIn,packedLightIn,packedOverlayIn);
    }
}