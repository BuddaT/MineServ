package net.buddat.mineserv.plr;

import org.apache.mina.core.session.IoSession;

public class Player {

	private IoSession session;
	
	private String playerName;

	public Player(IoSession ioSession) {
		session = ioSession;
	}
	
	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
