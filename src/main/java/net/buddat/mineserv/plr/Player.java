package net.buddat.mineserv.plr;

import net.buddat.mineserv.net.packet.PacketStore;

import org.apache.mina.core.session.IoSession;

public class Player {

	private IoSession session;
	
	private String playerName, playerTitle = "";
	private String connectionHash;
	
	private short health;
	
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

	public String getPlayerTitle() {
		return playerTitle;
	}

	public void setPlayerTitle(String playerTitle) {
		this.playerTitle = playerTitle;
	}

	public short getHealth() {
		return health;
	}

	public void setHealth(short health, boolean sendUpdate) {
		if (health > 20)
			health = 20;
		else if (health < 0)
			health = 0;
		
		this.health = health;
		
		if (sendUpdate)
			this.session.write(PacketStore.getHealthUpdatePacket(this.health));
	}
}
