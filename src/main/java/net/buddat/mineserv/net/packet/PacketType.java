package net.buddat.mineserv.net.packet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Represents a type of packet, from the client to the server or vice versa.
 * As packets of a given id currently have the same length regardless of
 * whether they are sent from the client to the server or vice versa, both
 * both directions are represented by a single packet type
 *
 * @author Taufiq Hoven
 */
public enum PacketType {
	// TODO: Add the rest of the javadoc.

	/**
	 * Keeps the connection alive.
	 */
	KEEP_ALIVE(0x0, 1),

	/**
	 * Request from the client to the server for login, and corresponding
	 * response from the server.
	 */
	LOGIN_REQUEST_RESPONSE(0x1),

	/**
	 * Handshake occurs as the first packet sent when the client connects.
	 */
	HANDSHAKE(0x2),

	/**
	 * Chat message sent from the client to the server or vice versa.
	 */
	CHAT(0x3),

	/**
	 * From the server to the client to update the in-game time.
	 */
	TIME_UPDATE(0x4, 9),

	/**
	 * Contains information on named entities' visible equipment.
	 */
	ENTITY_EQUIPMENT(0x5, 11),

	/**
	 * Sent by the server specifying the coordinates of the spawn point
	 * and where the compass points to.
	 */
	SPAWN_POSITION(0x6, 13),

	/**
	 * Sent by the client to the server when the client attacks or otherwise
	 * "uses" another entity.
	 */
	USE_ENTITY(0x7, 10),

	/**
	 * Server-sent update for the health of the player.
	 */
	UPDATE_HEALTH(0x8, 3),

	/**
	 * From the client or sever. Client-sent indicates that the player has
	 * pressed "Respawn". Server-sent indicates that the player entity is
	 * respawned.
	 */
	RESPAWN(0x9, 2),

	/**
	 * Client-initiated indicate whether the player is on the ground or
	 * airborne.
	 */
	PLAYER(0xA, 2),

	/**
	 * Client-initiated update indicating the player's movement.
	 */
	PLAYER_POSITION(0xB, 34),

	/**
	 * Client-initiated update indicating the direction in which the player is
	 * looking.
	 */
	PLAYER_LOOK(0xC, 10),

	/**
	 * Client-initiated combination of {@link #PLAYER_LOOK} and
	 * {@link #PLAYER_POSITION}.
	 */
	PLAYER_POSITION_LOOK(0xD, 42),

	PLAYER_DIGGING(0xE, 12),
	PLAYER_BLOCK_PLACEMENT(0xF),
	HOLDING_CHANGE(0x10, 3),
	USE_BED(0x11, 15),
	ANIMATION(0x12, 6),
	ENTITY_ACTION(0x13, 6),
	NAMED_ENTITY_SPAWN(0x14),
	PICKUP_SPAWN(0x15, 25),
	COLLECT_ITEM(0x16, 9),
	ADD_OBJECT_VEHICLE(0x17),
	MOB_SPAWN(0x18),
	ENTITY_PAINTING(0x19),
	STANCE_UPDATE(0x1B),
	ENTITY_VELOCITY(0x1C, 11),
	DESTROY_ENTITY(0x1D, 5),
	ENTITY(0x1E, 5),
	ENTITY_RELATIVE_MOVE(0x1F, 8),
	ENTITY_LOOK(0x20, 7),
	ENTITY_LOOK_RELATIVE_MOVE(0x21, 10),
	ENTITY_TELEPORT(0x22, 19),
	ENTITY_STATUS(0x26, 6),
	ATTACH_ENTITY(0x27, 9),
	ENTITY_METADATA(0x28),
	PRE_CHUNK(0x32, 10),
	MAP_CHUNK(0x33),
	MULTI_BLOCK_CHANGE(0x34),
	BLOCK_CHANGE(0x35, 12),
	BLOCK_ACTION(0x36, 13),
	EXPLOSION(0x3C),
	SOUND_EFFECT(0x3D, 18),
	NEW_INVALID_STATE(0x46, 2),
	THUNDERBOLT(0x47, 18),
	OPEN_WINDOW(0x64),
	CLOSE_WINDOW(0x65, 2),
	WINDOW_CLICK(0x66),
	SET_SLOT(0x67),
	WINDOW_ITEMS(0x68),
	UPDATE_PROGRESS_BAR(0x69, 6),
	TRANSACTION(0x6A, 5),
	UPDATE_SIGN(0x82),
	ITEM_DATA(0x83),
	INCREMENT_STATISTIC(0xC8, 6),
	DISCONNECT_KICK(0xFF),
	;

	private static final Map<Integer, PacketType> IDS_TO_PACKET_TYPES;

	static {
		HashMap<Integer, PacketType> idsToPacketTypes = new HashMap<Integer, PacketType>();
		for (PacketType packetType: values()) {
			idsToPacketTypes.put(packetType.getId(), packetType);
		}
		IDS_TO_PACKET_TYPES = Collections.unmodifiableMap(idsToPacketTypes);
	}

	private final int id;

	private final boolean isVariableLength;

	private final int length;

	/**
	 * Constructs a variable-length packet type
	 *
	 * @param id
	 *            Id of the packet type.
	 */
	private PacketType(int id) {
		this.id = id;
		this.isVariableLength = true;
		this.length = -1;
	}

	/**
	 * Constructs a fixed-length packet type.
	 *
	 * @param id
	 *            Id of the packet type.
	 * @param length
	 *            Length, in bytes, of packets of this type.
	 */
	private PacketType(int id, int length) {
		this.id = id;
		this.isVariableLength = false;
		this.length = length;
	}

	/**
	 * @return Unique id of the packet type. This may be shared by client- or
	 *         server-sent packets.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return true if the packet may have different lengths, otherwise false.
	 */
	public boolean isVariableLength() {
		return isVariableLength;
	}

	/**
	 * @return	Length of the packet, for fixed length packets. For variable
	 * length packets this return value is undefined.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns the PacketType that has the corresponding id, or null if none
	 * exist.
	 *
	 * @param id
	 *            Id of the PacketType to retrieve.
	 * @return PacketType of the specified id, or null if none exist.
	 */
	public PacketType getPacketTypeForId(int id) {
		return IDS_TO_PACKET_TYPES.get(id);
	}
}
