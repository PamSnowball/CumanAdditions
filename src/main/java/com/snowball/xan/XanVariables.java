package com.snowball.xan;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.client.Minecraft;

public class XanVariables {
	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "xan_mapvars";
		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
			return nbt;
		}

		public void syncData(World world) {
			this.markDirty();
			if (world.isRemote) {
				Xan.PACKET_HANDLER.sendToServer(new WorldSavedDataSyncMessage(0, this));
			} else {
				Xan.PACKET_HANDLER.sendToAll(new WorldSavedDataSyncMessage(0, this));
			}
		}

		public static MapVariables get(World world) {
			MapVariables instance = (MapVariables) world.getMapStorage().getOrLoadData(MapVariables.class, DATA_NAME);
			if (instance == null) {
				instance = new MapVariables();
				world.getMapStorage().setData(DATA_NAME, instance);
			}
			return instance;
		}
	}

	public static class WorldVariables extends WorldSavedData {
		public static final String DATA_NAME = "xan_worldvars";
		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void readFromNBT(NBTTagCompound nbt) {
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
			return nbt;
		}

		public void syncData(World world) {
			this.markDirty();
			if (world.isRemote) {
				Xan.PACKET_HANDLER.sendToServer(new WorldSavedDataSyncMessage(1, this));
			} else {
				Xan.PACKET_HANDLER.sendToDimension(new WorldSavedDataSyncMessage(1, this), world.provider.getDimension());
			}
		}

		public static WorldVariables get(World world) {
			WorldVariables instance = (WorldVariables) world.getPerWorldStorage().getOrLoadData(WorldVariables.class, DATA_NAME);
			if (instance == null) {
				instance = new WorldVariables();
				world.getPerWorldStorage().setData(DATA_NAME, instance);
			}
			return instance;
		}
	}

	public static class WorldSavedDataSyncMessageHandler implements IMessageHandler<WorldSavedDataSyncMessage, IMessage> {
		@Override
		public IMessage onMessage(WorldSavedDataSyncMessage message, MessageContext context) {
			if (context.side == Side.SERVER)
				context.getServerHandler().player.getServerWorld()
						.addScheduledTask(() -> syncData(message, context, context.getServerHandler().player.world));
			else
				Minecraft.getMinecraft().addScheduledTask(() -> syncData(message, context, Minecraft.getMinecraft().player.world));
			return null;
		}

		private void syncData(WorldSavedDataSyncMessage message, MessageContext context, World world) {
			if (context.side == Side.SERVER) {
				message.getData().markDirty();
				if (message.getType() == 0)
					Xan.PACKET_HANDLER.sendToAll(message);
				else
					Xan.PACKET_HANDLER.sendToDimension(message, world.provider.getDimension());
			}
			if (message.getType() == 0) {
				world.getMapStorage().setData(MapVariables.DATA_NAME, message.getData());
			} else {
				world.getPerWorldStorage().setData(WorldVariables.DATA_NAME, message.getData());
			}
		}
	}

	public static class WorldSavedDataSyncMessage implements IMessage {
		private int type;
		private WorldSavedData data;
		public WorldSavedDataSyncMessage() {
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.setType(type);
			this.setData(data);
		}

		@Override
		public void toBytes(io.netty.buffer.ByteBuf buf) {
			buf.writeInt(this.getType());
			ByteBufUtils.writeTag(buf, this.getData().writeToNBT(new NBTTagCompound()));
		}

		@Override
		public void fromBytes(io.netty.buffer.ByteBuf buf) {
			this.setType(buf.readInt());
			if (this.getType() == 0)
				this.setData(new MapVariables());
			else
				this.setData(new WorldVariables());
			this.getData().readFromNBT(ByteBufUtils.readTag(buf));
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public WorldSavedData getData() {
			return data;
		}

		public void setData(WorldSavedData data) {
			this.data = data;
		}
	}
}
