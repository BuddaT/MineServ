package net.buddat.mineserv.net.packet.handlers;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.buddat.mineserv.MineServ;
import net.buddat.mineserv.net.packet.Packet;
import net.buddat.mineserv.net.packet.PacketHandler;
import net.buddat.mineserv.options.Options;
import net.buddat.mineserv.plr.Player;
import net.buddat.mineserv.plr.PlayerManager;

public class PingHandler implements PacketHandler {

	/** The default logger for this class. */
	private final Logger logger = LoggerFactory.getLogger(PingHandler.class);
	
	private static String DELIMITER = "§";
	
	@Override
	public void handlePacket(Packet p) {
		IoSession session = p.getSession();
		Player plr = (Player) session.getAttribute("PLAYER");
		PlayerManager plrManager = MineServ.getInstance().getEngine().getPlayerManager();
		
		String pingString = "This server is the shit!" + DELIMITER + plrManager.getPlayerList().size() + DELIMITER + 
				(Integer) MineServ.getInstance().getOptions().getOption(Options.MAX_PLAYERS).getValue();

		logger.info(pingString);
		
		plrManager.disconnect(plr, pingString);
	}

}
