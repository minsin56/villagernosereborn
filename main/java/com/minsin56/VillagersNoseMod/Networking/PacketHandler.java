package com.minsin56.VillagersNoseMod.Networking;

import com.minsin56.VillagersNoseMod.VillagersNoseMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler
{
    private static final String ProtoVersion = "1";
    private static int ID = 0;

    public static final SimpleChannel Instance = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(VillagersNoseMod.ID,"main"),
            () -> ProtoVersion,
            ProtoVersion::equals,
            ProtoVersion::equals
    );

    public static void Register()
    {
        Instance.registerMessage(ID,ClientPacket.class,ClientPacket::Encode,ClientPacket::Decode,ClientPacket::Handle);
    }
}
