package com.minsin56.VillagersNoseMod.Items;

import com.minsin56.VillagersNoseMod.GUIScreens.ScreenOreNose;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemOreNose extends ItemVillagerNose implements INamedContainerProvider
{
    public Block OreToSniff;
    public Block OreBlockToSniff;
    private Container OreContainer;
    private Inventory OreInventory;

    public ItemOreNose()
    {
        super();

        OreInventory = new Inventory(1);

        OreContainer = new Container(ContainerType.GENERIC_3X3,0) {
            @Override
            public boolean canInteractWith(PlayerEntity playerIn) {
              return true;
            }
        };
        OreContainer.inventorySlots.clear();
        OreContainer.getInventory().clear();
        OreContainer.inventorySlots.add(new Slot(OreInventory,0,0,0));
    }

    @Override
    public Block[] GetSniffingBlocks()
    {
        if(OreToSniff != null && OreBlockToSniff != null)
        {
            return new Block[]{OreToSniff,OreBlockToSniff};
        }
        return super.GetSniffingBlocks();
    }
    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt)
    {
        if(nbt.contains("ore_to_sniff"))
        {
            OreToSniff = GameRegistry.findRegistry(Block.class).getValue(new ResourceLocation(nbt.getString("ore_to_sniff"))).getBlock();
        }
        else
        {
            nbt.putString("ore_to_sniff","minecraft:emerald_ore");
            OreToSniff = GameRegistry.findRegistry(Block.class).getValue(new ResourceLocation(nbt.getString("block_to_sniff"))).getBlock();

        }
        if(nbt.contains("block_to_sniff"))
        {
            OreBlockToSniff = GameRegistry.findRegistry(Block.class).getValue(new ResourceLocation(nbt.getString("block_to_sniff"))).getBlock();
        }
        else
        {
            nbt.putString("block_to_sniff","minecraft:diamond_ore");
            OreBlockToSniff = GameRegistry.findRegistry(Block.class).getValue(new ResourceLocation(nbt.getString("block_to_sniff"))).getBlock();
        }

        return super.updateItemStackNBT(nbt);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ScreenOreNose ScreenNose = new ScreenOreNose();
        Minecraft.getInstance().displayGuiScreen(ScreenNose);


        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_)
    {
        return OreContainer;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new StringTextComponent("Villager Nose");
    }
}
