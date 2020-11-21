package com.minsin56.VillagersNoseMod.Networking;

import com.minsin56.VillagersNoseMod.VillagerStuff.Nose.NoseProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientPacket
{
    private int EntityID;
    private boolean HasNose;

    public ClientPacket(int EntityID,boolean HasNose)
    {
        this.EntityID = EntityID;
        this.HasNose = HasNose;
    }

    static void Encode(ClientPacket MSG, PacketBuffer Buffer)
    {
        Buffer.writeBoolean(MSG.HasNose);
        Buffer.writeInt(MSG.EntityID);
    }

    static ClientPacket Decode(PacketBuffer Buffer)
    {
        boolean HasNose = Buffer.readBoolean();
        int ID = Buffer.readInt();

        return new ClientPacket(ID,HasNose);
    }

    static void Handle(ClientPacket MSG, Supplier<NetworkEvent.Context> CTX)
    {
        CTX.get().enqueueWork(() ->
        {
            ClientPlayerEntity ClientPlayer = Minecraft.getInstance().player;
            Entity Ent = Minecraft.getInstance().world.getEntityByID(MSG.EntityID);

            if(Ent != null && Ent.getCapability(NoseProvider.NoseCap).isPresent())
            {
                Ent.getCapability(NoseProvider.NoseCap).orElseThrow(NullPointerException::new).SetHasNose(MSG.HasNose);
            }
            else
            {
                throw new RuntimeException("SOMETHING HAPPEN HAAALP MEH");
            }
        });
        CTX.get().setPacketHandled(true);
    }
}
