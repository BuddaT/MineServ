package net.buddat.mineserv.net.packet.handlers;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.buddat.mineserv.MineServ;
import net.buddat.mineserv.net.packet.Packet;
import net.buddat.mineserv.net.packet.PacketHandler;
import net.buddat.mineserv.net.packet.PacketStore;
import net.buddat.mineserv.plr.Player;
import net.buddat.mineserv.plr.PlayerManager;

public class ChatHandler implements PacketHandler {
	
	private final Logger logger = LoggerFactory.getLogger(ChatHandler.class);

	@Override
	public void handlePacket(Packet p) {
		IoSession session = p.getSession();
		Player plr = (Player) session.getAttribute("PLAYER");
		
		if (p.getPayload() != null) {
			ByteBuffer buffer = ByteBuffer.wrap(p.getPayload());
			
			short msgLength = buffer.getShort();
			byte[] msg = new byte[msgLength * 2];
			
			buffer.get(msg);
			
			String message = "";
			try {
				message = new String(msg, PacketStore.UTF16);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			if (message.startsWith("/"))
				processCommand(plr, message);
			else
				processChat(plr, message);
		}
		
	}

	private void processCommand(Player p, String message) {
		/*
		 * Commands go here
		 */
	}

	private void processChat(Player p, String message) {
		PlayerManager plrManager = MineServ.getInstance().getEngine().getPlayerManager();
		
		if (message.startsWith("./"))
			message = message.substring(1, message.length());
		
		String toSend = "<" + p.getPlayerTitle() + "> " + p.getPlayerName() + ": " + message;
		
		plrManager.massWrite(PacketStore.getChatPacket(toSend));
	}

}
