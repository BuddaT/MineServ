package net.buddat.mineserv.plr;

import net.buddat.mineserv.net.packet.PacketStore;

import org.apache.mina.core.session.IoSession;

/**
 * Player class. Contains all the information about connected players.
 * 
 * @author Budda
 */
public class Player {

	/** The player's session. */
	private IoSession session;
	
	/** Player name and title.*/
	private String playerName, playerTitle = "";
	
	/** Connection hash sent during handshake. */
	private String connectionHash;
	
	/** Player's health. */
	private short health;
	
	/** Whether the player is connected or not.. */
	private boolean connected = false;

	/**
	 * Creates a new player with the specified session.
	 *
	 * @param ioSession the io session
	 */
	public Player(IoSession ioSession) {
		session = ioSession;
	}
	
	/**
	 * Gets the session.
	 *
	 * @return the player's session
	 */
	public IoSession getSession() {
		return session;
	}

	/**
	 * Sets the player's session.
	 *
	 * @param session the new session
	 */
	public void setSession(IoSession session) {
		this.session = session;
	}

	/**
	 * Gets the player name.
	 *
	 * @return the player name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Sets the player name.
	 *
	 * @param playerName the new player name
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Gets the connection hash.
	 *
	 * @return the connection hash
	 */
	public String getConnectionHash() {
		return connectionHash;
	}

	/**
	 * Sets the connection hash.
	 *
	 * @param connectionHash the new connection hash
	 */
	public void setConnectionHash(String connectionHash) {
		this.connectionHash = connectionHash;
	}
	
	/**
	 * Sets whether the player is connected or not.
	 *
	 * @param connected true if player is connected
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * Checks if the player is connected.
	 *
	 * @return true, if player is connected
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Gets the player title.
	 *
	 * @return the player title
	 */
	public String getPlayerTitle() {
		return playerTitle;
	}

	/**
	 * Sets the player title.
	 *
	 * @param playerTitle the new player title
	 */
	public void setPlayerTitle(String playerTitle) {
		this.playerTitle = playerTitle;
	}

	/**
	 * Gets the player's health.
	 *
	 * @return the health
	 */
	public short getHealth() {
		return health;
	}

	/**
	 * Sets the player's health and send the update_health packet to the player.
	 *
	 * @param health the health
	 * @param sendUpdate whether to send the update packet or not
	 */
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
