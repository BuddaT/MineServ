package net.buddat.mineserv;

import net.buddat.mineserv.plr.PlayerManager;

public class Engine {

	private PlayerManager playerManager;
	
	public Engine() {
		playerManager = new PlayerManager();
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}

	
}
