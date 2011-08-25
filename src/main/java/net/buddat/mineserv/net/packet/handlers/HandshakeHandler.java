package net.buddat.mineserv.net.packet.handlers;

import java.io.UnsupportedEncodingException;

import org.apache.mina.core.session.IoSession;

import net.buddat.mineserv.net.packet.Packet;
import net.buddat.mineserv.net.packet.PacketBuilder;
import net.buddat.mineserv.net.packet.PacketHandler;
import net.buddat.mineserv.plr.Player;
import net.buddat.mineserv.util.Logger;

public class HandshakeHandler implements PacketHandler {

	@Override
	public void handlePacket(Packet p) {
		IoSession session = p.getSession();
		Player plr = (Player) session.getAttribute("PLAYER");
		
		if (p.getPayload() != null) {
			byte[] payload = p.getPayload();
			byte[] name = new byte[payload.length - 2];
			
			System.arraycopy(payload, 2, name, 0, name.length);
			
			try {
				String s = new String(name, "UTF-16BE");
				plr.setPlayerName(s);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			String connectionHash = Long.toHexString(session.getId());
			plr.setConnectionHash(connectionHash);
			
			int packetLength = 2 + (connectionHash.length() * 2); //Needs to be (2+(length*2)) due to 2 bytes per char in UTF-16 (plus 2 for the length short)
	
			session.write(new PacketBuilder(packetLength).setId(2).addString(connectionHash, "UTF-16BE").toPacket());
			
			Logger.log("New Handshake: " + plr.getPlayerName());
		}
	}

}
