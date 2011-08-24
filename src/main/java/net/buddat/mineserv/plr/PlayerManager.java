package net.buddat.mineserv.plr;

import java.util.ArrayList;

import org.apache.mina.core.session.IoSession;

public class PlayerManager {

	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	public PlayerManager() { }
	
	public void init() {
		
	}
	
	public void tick() {
		
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	
	public void addPlayer(Player p) {
		playerList.add(p);
	}
	
	public Player getPlayer(int id) {
		return playerList.get(id);
	}
	
	public Player getPlayer(IoSession session) {
		for (Player p : playerList) {
			if (p.getSession() == session)
				return p;
		}
		
		return null;
	}

	public void removePlayer(Player p) {
		playerList.remove(p);
	}
}
