package net.buddat.mineserv.net.packet;

import org.apache.mina.core.session.IoSession;

public class Packet {
	
	private IoSession session;
	private int packetId;
	private byte[] payload;

	public Packet(IoSession session, int id, byte[] payload) {
		this.session = session;
		this.packetId = id;
		this.payload = payload;
	}

	public IoSession getSession() {
		return session;
	}

	public int getPacketId() {
		return packetId;
	}

	public byte[] getPayload() {
		return payload;
	}

}
