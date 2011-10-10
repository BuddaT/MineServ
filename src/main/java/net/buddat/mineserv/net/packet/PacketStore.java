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
	 * @see PacketType#RESPAWN
	 *
	 * @param world the world
	 * @return the respawn packet
	 */
	public static Packet getRespawnPacket(byte world) {
		PacketType packetType = PacketType.RESPAWN;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addByte(world).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#PLAYER_POSITION_LOOK
	 *
	 * @param x player x position
	 * @param y player y position
	 * @param z player z position
	 * @param stance player stance
	 * @param yaw player yaw
	 * @param pitch player pitch
	 * @param onGround true if player is on the ground
	 * @return the player position and look packet
	 */
	public static Packet getPlayerPosAndLookPacket(double x, double y, double z, double stance, float yaw, float pitch, boolean onGround) {
		PacketType packetType = PacketType.PLAYER_POSITION_LOOK;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addDouble(x).addDouble(stance).addDouble(y).addDouble(z).addFloat(yaw)
				.addFloat(pitch).addByte((byte) (onGround == false ? 0 : 1)).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#USE_BED
	 *
	 * @param entityID the entity id
	 * @param inBed 0 if player uses bed
	 * @param x x pos of bed headboard
	 * @param y y pos of bed headboard
	 * @param z z pos of bed headboard
	 * @return the use bed packet
	 */
	public static Packet getUseBedPacket(int entityId, byte inBed, int x, byte y, int z) {
		PacketType packetType = PacketType.USE_BED;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addInt(entityId).addByte(inBed).addInt(x).addByte(y).addInt(z).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#NAMED_ENTITY_SPAWN
	 *
	 * @param entityId the entity id
	 * @param name the entity's name
	 * @param x x position
	 * @param y y position
	 * @param z z position
	 * @param rotation the rotation
	 * @param pitch the pitch
	 * @param itemHeld the item held (0 for nothing, -1 causes crash)
	 * @return the named entity spawn packet
	 */
	public static Packet getNamedEntitySpawnPacket(int entityId, String name, int x, int y, int z, byte rotation, byte pitch, short itemHeld) {
		PacketType packetType = PacketType.NAMED_ENTITY_SPAWN;
		
		int packetLength = 20 + 2 + (name.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(packetType.getId())
				.addInt(entityId).addString(name, UTF16).addInt(x).addInt(y).addInt(z)
				.addByte(rotation).addByte(pitch).addShort(itemHeld).toPacket();
		
		return packet;
	}
	
	/**
	 * @see PacketType#PICKUP_SPAWN
	 *
	 * @param entityId the entity id
	 * @param itemId the item id
	 * @param count the item count
	 * @param damageOrData the damage or data
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @param rotation the rotation
	 * @param pitch the pitch
	 * @param roll the roll
	 * @return the pickup spawn packet
	 */
	public static Packet getPickupSpawnPacket(int entityId, short itemId, byte count, short damageOrData, int x, int y, int z, byte rotation, byte pitch, byte roll) {
		PacketType packetType = PacketType.PICKUP_SPAWN;
		
		Packet packet = new PacketBuilder(packetType.getLength()).setId(packetType.getId())
				.addInt(entityId).addShort(itemId).addByte(count).addShort(damageOrData)
				.addInt(x).addInt(y).addInt(z).addByte(rotation).addByte(pitch).addByte(roll).toPacket();
		
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
