package net.buddat.mineserv.net.packet;

import org.apache.mina.core.session.IoSession;

/**
 * Wrapper class for incoming and outgoing packets.
 * 
 * @author Budda
 */
public class Packet {
	
	/** The session. Used for incoming packets only.*/
	private IoSession session;
	
	/** Id of the packet. */
	private int packetId;
	
	/** Byte array containing the packet information. */
	private byte[] payload;

	/**
	 * Creates a new empty packet.
	 */
	protected Packet() { }

	/**
	 * Creates a new packet with the specified session, id and payload.
	 *
	 * @param session the session if incoming packet
	 * @param id id of the packet
	 * @param payload the payload
	 */
	public Packet(IoSession session, int id, byte[] payload) {
		this.session = session;
		this.packetId = id;
		this.payload = payload;
	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	public IoSession getSession() {
		return session;
	}

	/**
	 * Gets the packet id.
	 *
	 * @return the packet id
	 */
	public int getPacketId() {
		return packetId;
	}

	/**
	 * Gets the payload.
	 *
	 * @return the payload
	 */
	public byte[] getPayload() {
		return payload;
	}
	
	/**
	 * Sets the session.
	 *
	 * @param session the new session
	 */
	public void setSession(IoSession session) {
		this.session = session;
	}

	/**
	 * Sets the packet id.
	 *
	 * @param packetId the new packet id
	 */
	public void setPacketId(int packetId) {
		this.packetId = packetId;
	}

	/**
	 * Sets the payload.
	 *
	 * @param payload the new payload
	 */
	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

}
