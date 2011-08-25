package net.buddat.mineserv.net.packet;

import org.apache.mina.core.session.IoSession;

public interface PacketHandler {

	public void handlePacket(Packet p);
	
}
