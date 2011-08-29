package net.buddat.mineserv.net.packet;

import java.io.UnsupportedEncodingException;

/**
 * Static class for building outgoing packets easily.
 * 
 * @author Budda
 */
public class PacketBuilder {

	/** The payload. */
	private byte[] payload;
	
	/** Current pointer position in {@link #payload} */
	private int curLength = 0;
	
	/** Packet id. */
	private int id;
	
	/**
	 * Creates a new PacketBuilder with the specified array size.
	 *
	 * @param size size of payload array
	 */
	public PacketBuilder(int size) {
		payload = new byte[size];
	}
	
	/**
	 * Sets the packet id.
	 *
	 * @param id the id
	 * @return the packet builder
	 */
	public PacketBuilder setId(int id) {
		this.id = id;
		return this;
	}
	
	/**
	 * Adds a byte array to the payload.
	 *
	 * @param b byte array to add
	 * @return the packet builder
	 */
	public PacketBuilder addBytes(byte[] b) {
		System.arraycopy(b, 0, payload, curLength, b.length);
		curLength += b.length;
		
		return this;
	}
	
	/**
	 * Adds a byte to the payload.
	 *
	 * @param b byte to add
	 * @return the packet builder
	 */
	public PacketBuilder addByte(byte b) {
		payload[curLength++] = b;
		
		return this;
	}
	
	/**
	 * Adds a short to the payload
	 *
	 * @param s short to add
	 * @return the packet builder
	 */
	public PacketBuilder addShort(short s) {
		addByte((byte) (s >> 8));
		addByte((byte) s);
		
		return this;
	}
	
	/**
	 * Adds an int to the payload.
	 *
	 * @param i int to add
	 * @return the packet builder
	 */
	public PacketBuilder addInt(int i) {
		addByte((byte) (i >> 24));
		addByte((byte) (i >> 16));
		addByte((byte) (i >> 8));
		addByte((byte) i);
		
		return this;
	}
	
	/**
	 * Adds a long to the payload.
	 *
	 * @param l long to add
	 * @return the packet builder
	 */
	public PacketBuilder addLong(long l) {
		addInt((int) (l >> 32));
		addInt((int) (l & -1L));
		
		return this;
	}
	
	/**
	 * Adds a string to the payload. 
	 * Default encoding is UTF-16BE - because of this it adds a short for the
	 * String length first, followed by the encoded String bytes.
	 *
	 * @param s string to add
	 * @param encoding string encoding
	 * @return the packet builder
	 */
	public PacketBuilder addString(String s, String encoding) {
		if (!encoding.equals(PacketStore.UTF16) && !encoding.equals(PacketStore.UTF8))
			encoding = PacketStore.UTF16;
		
		try {
			byte[] sBytes = s.getBytes(encoding);
			
			addShort((short) s.length());
			System.arraycopy(sBytes, 0, payload, curLength, sBytes.length);
			
			curLength += sBytes.length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Wraps everything into a Packet.
	 *
	 * @return the packet
	 */
	public Packet toPacket() {
		return new Packet(null, id, payload);
	}

}
