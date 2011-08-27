package net.buddat.mineserv.net.packet;

import net.buddat.mineserv.net.codec.MinecraftCodecFactory;

public class PacketStore {
	
	public static String UTF16 = "UTF-16BE";
	public static String UTF8 = "UTF-8";
	
	public static Packet getLoginResponsePacket(int entityId, long mapSeed, byte dimension) {
		return getLoginResponsePacket(entityId, null, mapSeed, dimension);
	}

	public static Packet getLoginResponsePacket(int entityId, String unknown, long mapSeed, byte dimension) {
		int packetId = 1;
		
		int packetLength = 15 + (unknown != null ? (unknown.length() * 2) : 0);
		Packet packet = new PacketBuilder(packetLength).setId(packetId)
				.addInt(entityId).addString(unknown, UTF16).addLong(mapSeed).addByte(dimension).toPacket();
		
		return packet;
	}
	
	public static Packet getHandshakePacket(String connHash) {
		int packetId = 2;
		
		int packetLength = 2 + (connHash.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(packetId)
				.addString(connHash, UTF16).toPacket();
		
		return packet;
	}
	
	public static Packet getChatPacket(String msg) {
		int packetId = 3;
		
		int packetLength = 2 + (msg.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(packetId)
				.addString(msg, UTF16).toPacket();
		
		return packet;
	}
	
	public static Packet getTimePacket(long time) {
		int packetId = 4;
		
		Packet packet = new PacketBuilder(MinecraftCodecFactory.getPacketLength(packetId)).setId(packetId)
				.addLong(time).toPacket();
		
		return packet;
	}
	
	public static Packet getEntityEquipPacket(int entityId, short equipSlot, short itemId, short dmg) {
		int packetId = 5;
		
		Packet packet = new PacketBuilder(MinecraftCodecFactory.getPacketLength(packetId)).setId(packetId)
				.addInt(entityId).addShort(equipSlot).addShort(itemId).addShort(dmg).toPacket();
		
		return packet;
	}
	
	public static Packet getSpawnPosPacket(int xPos, int yPos, int zPos) {
		int packetId = 6;
		
		Packet packet = new PacketBuilder(MinecraftCodecFactory.getPacketLength(packetId)).setId(packetId)
				.addInt(xPos).addInt(yPos).addInt(zPos).toPacket();
		
		return packet;
	}
	
	public static Packet getHealthUpdatePacket(short newHealth) {
		int packetId = 8;
		
		Packet packet = new PacketBuilder(MinecraftCodecFactory.getPacketLength(packetId)).setId(packetId)
				.addShort(newHealth).toPacket();
		
		return packet;
	}
	
	public static Packet getKickPacket(String reason) {
		int packetId = 255;
		
		int packetLength = 2 + (reason.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(packetId)
				.addString(reason, UTF16).toPacket();
		
		return packet;
	}
}
