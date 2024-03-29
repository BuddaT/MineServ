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

	/**
	 * Client-initiated when the player mines a block.
	 */
	PLAYER_DIGGING(0xE, 12),

	/**
	 * Client-initiated when the player places a block or item, or when using
	 * a block/item.
	 */
	PLAYER_BLOCK_PLACEMENT(0xF),

	/**
	 * Client-initiated when the player changes the active slot selection.
	 */
	HOLDING_CHANGE(0x10, 3),

	/**
	 * Server-initiated when two or more players are on a server and at least
	 * one of them uses a bed.
	 */
	USE_BED(0x11, 15),

	/**
	 * Client-initiated when an entity is to change its animation.
	 */
	ANIMATION(0x12, 6),

	/**
	 * Client-initiated when an entity performs an action.
	 */
	ENTITY_ACTION(0x13, 6),

	/**
	 * Server-initiated when a named entity (ie. player) comes into visible
	 * range. This must not ever refer to the receiver's own username or entity
	 * id.
	 */
	NAMED_ENTITY_SPAWN(0x14),

	/**
	 * Server-initiated when an item comes into visible item range of the
	 * player.
	 */
	PICKUP_SPAWN(0x15, 25),

	/**
	 * Server-initiated when an item is picked up by a player, including
	 * both the receiver and players other than the receiver.
	 */
	COLLECT_ITEM(0x16, 9),

	/**
	 * Server-initiated when an object/vehicle is created.
	 */
	ADD_OBJECT_VEHICLE(0x17),

	/**
	 * Server-initiated when a mob entity is spawned.
	 */
	MOB_SPAWN(0x18),

	/**
	 * Shows the location, name and type of painting.
	 */
	ENTITY_PAINTING(0x19),

	/**
	 * Does not appear to be used.
	 */
	STANCE_UPDATE(0x1B),

	/**
	 * Entity velicity/motion.
	 */
	ENTITY_VELOCITY(0x1C, 11),

	/**
	 * Server-initiated when an entity is to be destroyed.
	 */
	DESTROY_ENTITY(0x1D, 5),

	/**
	 * Server-initiated to indicate that the entity did not move/look since
	 * this or any other entity packet.
	 */
	ENTITY(0x1E, 5),

	/**
	 * Server-initiated when an entity moves at most four blocks. For
	 * movement of more than four blocks {@link #ENTITY_TELEPORT} is used.
	 */
	ENTITY_RELATIVE_MOVE(0x1F, 8),

	/**
	 * Server-initiated when an entity rotates.
	 */
	ENTITY_LOOK(0x20, 7),

	/**
	 * Server-initiated when an entity both rotates and moves up to four
	 * blocks.
	 */
	ENTITY_LOOK_RELATIVE_MOVE(0x21, 10),

	/**
	 * Server-initiated when an entity moves more than four blocks.
	 */
	ENTITY_TELEPORT(0x22, 19),

	/**
	 * Server-initiated indicating entity damage, death and explosion.
	 */
	ENTITY_STATUS(0x26, 6),

	/**
	 * Server-initiated indicated that a player is attached to an entity.
	 */
	ATTACH_ENTITY(0x27, 9),

	/**
	 * Extra entity information.
	 */
	ENTITY_METADATA(0x28),

	/**
	 * Server-initiated notifying the client to initialise or unload a chunk.
	 */
	PRE_CHUNK(0x32, 10),

	/**
	 * Server-initiated for the client to overwrite all or part of a chunk with
	 * the contents of the packet.
	 */
	MAP_CHUNK(0x33),

	/**
	 * Multiple blocks changed.
	 */
	MULTI_BLOCK_CHANGE(0x34),

	/**
	 * Block changed.
	 */
	BLOCK_CHANGE(0x35, 12),

	/**
	 * Server-initiated when a block performs an action (eg. note block
	 * played or a piston changed state).
	 */
	BLOCK_ACTION(0x36, 13),

	/**
	 * Server-initiated when an explosion occurs.
	 */
	EXPLOSION(0x3C),

	/**
	 * Server-initiated when a client is to play an effect.
	 */
	SOUND_EFFECT(0x3D, 18),

	/**
	 * Server-initiated when a bed can't be used as a spawn point and when the
	 * rain state changes.
	 */
	NEW_INVALID_STATE(0x46, 2),

	/**
	 * Server-initiated when a thunderbolt strikes nearby.
	 */
	THUNDERBOLT(0x47, 18),

	/**
	 * Server-initiated when the client should open an inventory window.
	 */
	OPEN_WINDOW(0x64),

	/**
	 * Client-initiated when the an inventory window is closed, and
	 * server-initiated when an inventory window should be forcible
	 * closed.
	 */
	CLOSE_WINDOW(0x65, 2),

	/**
	 * Client-initiated when the player clicks on a slot in an inventory
	 * window.
	 */
	WINDOW_CLICK(0x66),

	/**
	 * Server-initiated when an item is added to or removed from an inventory
	 * window slot. Only partially documented.
	 */
	SET_SLOT(0x67),

	/**
	 * Server-initiated when an item is added to or removed from an inventory
	 * window slot.
	 */
	WINDOW_ITEMS(0x68),

	/**
	 * Server-initiated when a furnace progress bar is updated.
	 */
	UPDATE_PROGRESS_BAR(0x69, 6),

	/**
	 * Server-initiated indicating whether a request from the client was
	 * accepted or rejected (due to conflict). Client-initiated in response
	 * to a server packet of the same type.
	 */
	TRANSACTION(0x6A, 5),

	/**
	 * Server-initiated when a sign is discovered or created. Client-initiated
	 * when a signs has been labelled (by pressing the "Done" button after
	 * placing a sign).
	 */
	UPDATE_SIGN(0x82),

	/**
	 * Server-initiated to specify complex data on an item, currently only
	 * applicable to maps.
	 */
	ITEM_DATA(0x83),

	/**
	 * Server-initiated to increment a statistic.
	 */
	INCREMENT_STATISTIC(0xC8, 6),
	
	/**
	 * Sent to server to retrieve MOTD and player-count
	 */
	SERVER_LIST_PING(0xFE),

	/**
	 * Server-initiated before it disconnects a client, and client-initiated
	 * before it disconnects.
	 */
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
	public static PacketType getPacketTypeForId(int id) {
		return IDS_TO_PACKET_TYPES.get(id);
	}
}
