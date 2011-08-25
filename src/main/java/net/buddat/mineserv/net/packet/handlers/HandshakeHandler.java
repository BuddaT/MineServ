package net.buddat.mineserv.net.packet.handlers;

import net.buddat.mineserv.net.packet.Packet;
import net.buddat.mineserv.net.packet.PacketHandler;
import net.buddat.mineserv.plr.Player;
import net.buddat.mineserv.util.Logger;

public class HandshakeHandler implements PacketHandler {

	@Override
	public void handlePacket(Packet p) {
		Logger.log("New Handshake: " + ((Player) p.getSession().getAttribute("PLAYER")).getPlayerName());
	}

}
