package net.buddat.mineserv.net.packet;

public class PacketStore {
	
	public static String UTF16 = "UTF-16BE";
	public static String UTF8 = "UTF-8";
	
	public static Packet getLoginResponsePacket(int entityId, long mapSeed, byte dimension) {
		return getLoginResponsePacket(entityId, null, mapSeed, dimension);
	}

	public static Packet getLoginResponsePacket(int entityId, String unknown, long mapSeed, byte dimension) {
		PacketType packetType = PacketType.LOGIN_REQUEST_RESPONSE;
		
		int packetLength = 15 + (unknown != null ? (unknown.length() * 2) : 0);
		Packet packet = new PacketBuilder(packetLength).setId(packetType.getId())
				.addInt(entityId).addString(unknown, UTF16).addLong(mapSeed).addByte(dimension).toPacket();
		
		return packet;
	}
	
	public static Packet getHandshakePacket(String connHash) {
		PacketType packetType = PacketType.HANDSHAKE;
		
		int packetLength = 2 + (connHash.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(packetType.getId())
				.addString(connHash, UTF16).toPacket();
		
		return packet;
	}
	
	public static Packet getChatPacket(String msg) {
		PacketType packetType = PacketType.CHAT;
		
		int packetLength = 2 + (msg.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(packetType.getId())
				.addString(msg, UTF16).toPacket();
		
		return packet;
	}
	
	public static Packet getTimePacket(long time) {
		PacketType packetType = PacketType.TIME_UPDATE;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addLong(time).toPacket();
		
		return packet;
	}
	
	public static Packet getEntityEquipPacket(int entityId, short equipSlot, short itemId, short dmg) {
		PacketType packetType = PacketType.ENTITY_EQUIPMENT;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addInt(entityId).addShort(equipSlot).addShort(itemId).addShort(dmg).toPacket();
		
		return packet;
	}
	
	public static Packet getSpawnPosPacket(int xPos, int yPos, int zPos) {
		PacketType packetType = PacketType.SPAWN_POSITION;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addInt(xPos).addInt(yPos).addInt(zPos).toPacket();
		
		return packet;
	}
	
	public static Packet getHealthUpdatePacket(short newHealth) {
		PacketType packetType = PacketType.UPDATE_HEALTH;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addShort(newHealth).toPacket();
		
		return packet;
	}
	
	public static Packet getKickPacket(String reason) {
		PacketType packetType = PacketType.DISCONNECT_KICK;
		
		int packetLength = 2 + (reason.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(packetType.getId())
				.addString(reason, UTF16).toPacket();
		
		return packet;
	}
}
