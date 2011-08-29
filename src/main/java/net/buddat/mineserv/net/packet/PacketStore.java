package net.buddat.mineserv.net.packet;

/**
 * Contains static methods to build the different outgoing packets.
 * 
 * @author Budda
 */
public class PacketStore {
	
	/** Static string for referencing UTF-16BE encoding for strings. */
	public static String UTF16 = "UTF-16BE";
	
	/** Static string for referencing UTF-8 encoding for strings. */
	public static String UTF8 = "UTF-8";
	
	/**
	 * Creates a Login response Packet without the unknown string.
	 * 
	 * @see PacketType#LOGIN_REQUEST_RESPONSE
	 *
	 * @param entityId the entity id
	 * @param mapSeed the map seed
	 * @param dimension the dimension
	 * @return the login response packet
	 */
	public static Packet getLoginResponsePacket(int entityId, long mapSeed, byte dimension) {
		return getLoginResponsePacket(entityId, null, mapSeed, dimension);
	}

	/**
	 * @see PacketType#LOGIN_REQUEST_RESPONSE
	 *
	 * @param entityId the entity id
	 * @param unknown the unknown
	 * @param mapSeed the map seed
	 * @param dimension the dimension
	 * @return the login response packet
	 */
	public static Packet getLoginResponsePacket(int entityId, String unknown, long mapSeed, byte dimension) {
		PacketType packetType = PacketType.LOGIN_REQUEST_RESPONSE;
		
		int packetLength = 15 + (unknown != null ? (unknown.length() * 2) : 0);
		Packet packet = new PacketBuilder(packetLength).setId(packetType.getId())
				.addInt(entityId).addString(unknown, UTF16).addLong(mapSeed).addByte(dimension).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#HANDSHAKE
	 *
	 * @param connHash the conn hash
	 * @return the handshake packet
	 */
	public static Packet getHandshakePacket(String connHash) {
		PacketType packetType = PacketType.HANDSHAKE;
		
		int packetLength = 2 + (connHash.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(packetType.getId())
				.addString(connHash, UTF16).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#CHAT
	 *
	 * @param msg the msg
	 * @return the chat packet
	 */
	public static Packet getChatPacket(String msg) {
		PacketType packetType = PacketType.CHAT;
		
		int packetLength = 2 + (msg.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(packetType.getId())
				.addString(msg, UTF16).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#TIME_UPDATE
	 *
	 * @param time the time
	 * @return the time packet
	 */
	public static Packet getTimePacket(long time) {
		PacketType packetType = PacketType.TIME_UPDATE;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addLong(time).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#ENTITY_EQUIPMENT
	 *
	 * @param entityId the entity id
	 * @param equipSlot the equip slot
	 * @param itemId the item id
	 * @param dmg the dmg
	 * @return the entity equip packet
	 */
	public static Packet getEntityEquipPacket(int entityId, short equipSlot, short itemId, short dmg) {
		PacketType packetType = PacketType.ENTITY_EQUIPMENT;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addInt(entityId).addShort(equipSlot).addShort(itemId).addShort(dmg).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#SPAWN_POSITION
	 *
	 * @param xPos the x pos
	 * @param yPos the y pos
	 * @param zPos the z pos
	 * @return the spawn pos packet
	 */
	public static Packet getSpawnPosPacket(int xPos, int yPos, int zPos) {
		PacketType packetType = PacketType.SPAWN_POSITION;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addInt(xPos).addInt(yPos).addInt(zPos).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#UPDATE_HEALTH
	 *
	 * @param newHealth the new health
	 * @return the health update packet
	 */
	public static Packet getHealthUpdatePacket(short newHealth) {
		PacketType packetType = PacketType.UPDATE_HEALTH;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addShort(newHealth).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#DISCONNECT_KICK
	 *
	 * @param reason the reason
	 * @return the kick packet
	 */
	public static Packet getKickPacket(String reason) {
		PacketType packetType = PacketType.DISCONNECT_KICK;
		
		int packetLength = 2 + (reason.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(packetType.getId())
				.addString(reason, UTF16).toPacket();
		
		return packet;
	}
}
