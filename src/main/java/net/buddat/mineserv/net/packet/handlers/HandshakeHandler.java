package net.buddat.mineserv.net.packet.handlers;

import java.io.UnsupportedEncodingException;

import org.apache.mina.core.session.IoSession;

import net.buddat.mineserv.net.packet.Packet;
import net.buddat.mineserv.net.packet.PacketHandler;
import net.buddat.mineserv.net.packet.PacketStore;
import net.buddat.mineserv.plr.Player;

/**
 * Handles incoming Handshake packets.
 * 
 * @author Budda
 */
public class HandshakeHandler implements PacketHandler {

	/**
	 * Grabs the player name from the packet, and send the response packet
	 * with the player's connectionHash.
	 * 
	 * @see net.buddat.mineserv.net.packet.PacketHandler#handlePacket(net.buddat.mineserv.net.packet.Packet)
	 */
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
