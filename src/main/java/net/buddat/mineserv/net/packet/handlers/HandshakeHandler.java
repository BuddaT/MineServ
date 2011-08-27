package net.buddat.mineserv.net.packet.handlers;

import java.io.UnsupportedEncodingException;

import org.apache.mina.core.session.IoSession;

import net.buddat.mineserv.net.packet.Packet;
import net.buddat.mineserv.net.packet.PacketHandler;
import net.buddat.mineserv.net.packet.PacketStore;
import net.buddat.mineserv.plr.Player;

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
				String s = new String(name, PacketStore.UTF16);
				plr.setPlayerName(s);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			String connectionHash = Long.toHexString(session.getId());
			plr.setConnectionHash(connectionHash);
	
			session.write(PacketStore.getHandshakePacket(connectionHash));
		}
	}

}
