package net.buddat.mineserv.net;

import net.buddat.mineserv.MineServ;
import net.buddat.mineserv.plr.Player;
import net.buddat.mineserv.plr.PlayerManager;
import net.buddat.mineserv.util.Logger;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ConnectionHandler implements IoHandler {
	
	private PlayerManager plrManager;
	
	public ConnectionHandler() {
		plrManager = MineServ.getInstance().getEngine().getPlayerManager();
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable arg1)
			throws Exception {
		
	}

	@Override
	public void messageReceived(IoSession session, Object arg1) throws Exception {
		
	}

	@Override
	public void messageSent(IoSession session, Object arg1) throws Exception {
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		Player p = plrManager.getPlayer(session);
		
		if (p != null) {
			plrManager.removePlayer(p);
			Logger.log("Player Disconnected: " + p.getPlayerName());
		}
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		Logger.log("New Connection: " + session.getRemoteAddress());
		
		Player newPlayer = new Player(session);
		plrManager.addPlayer(newPlayer);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus arg1) throws Exception {
		
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		
	}

}
