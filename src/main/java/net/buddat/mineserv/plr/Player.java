package net.buddat.mineserv.plr;

import org.apache.mina.core.session.IoSession;

public class Player {

	private IoSession session;
	
	private String playerName;
	private String connectionHash;
	
	private boolean connected = false;

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

	public String getConnectionHash() {
		return connectionHash;
	}

	public void setConnectionHash(String connectionHash) {
		this.connectionHash = connectionHash;
	}
	
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public boolean isConnected() {
		return connected;
	}
}
