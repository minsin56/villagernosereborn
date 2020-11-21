package com.minsin56.VillagersNoseMod.GUIScreens;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ScreenOreNose extends Screen
{
    public ScreenOreNose()
    {
        super(new StringTextComponent("Villager Nose"));
    }

    @Override
    public void init(Minecraft minecraft, int width, int height)
    {

        super.init(minecraft, width, height);

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        renderBackground(matrixStack);
        font.drawString(matrixStack,"Farts",100,100,100);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }



    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
