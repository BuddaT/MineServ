package net.buddat.mineserv.plr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import net.buddat.mineserv.MineServ;
import net.buddat.mineserv.net.packet.PacketStore;

import org.apache.mina.core.session.IoSession;

/**
 * Manages all the players connected to the server.
 * 
 * @author Budda
 */
public class PlayerManager {

	/** List of connected players. */
	private ArrayList<Player> playerList = new ArrayList<Player>();
	
	/** List of banned player names. */
	private ArrayList<String> bannedList = new ArrayList<String>();
	
	/** List of banned ip addresses. */
	private ArrayList<String> ipBannedList = new ArrayList<String>();
	
	/**
	 * Instantiates a new player manager.
	 */
	public PlayerManager() { }
	
	/**
	 * Called when Engine inits to load everything before running.
	 * Currently loads the ban lists.
	 */
	public void init() {
		loadBanList();
	}
	
	/**
	 * Called each tick of the engine. Used for updating server-run player things.
	 */
	public void tick() {
		
	}

	/**
	 * Gets the player list.
	 *
	 * @return the player list
	 */
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * Sets the player list.
	 *
	 * @param playerList the new player list
	 */
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	
	/**
	 * Adds players to the connected list.
	 *
	 * @param p the player
	 */
	public void addPlayer(Player p) {
		playerList.add(p);
	}
	
	/**
	 * Gets the player from the connected list.
	 *
	 * @param id the id of the player
	 * @return the player
	 */
	public Player getPlayer(int id) {
		return playerList.get(id);
	}
	
	/**
	 * Gets the player via the session.
	 *
	 * @param session the session
	 * @return the player
	 */
	public Player getPlayer(IoSession session) {
		for (Player p : playerList) {
			if (p.getSession() == session)
				return p;
		}
		
		return null;
	}

	/**
	 * Removes the player from the connected list.
	 *
	 * @param p the player to remove
	 */
	public void removePlayer(Player p) {
		playerList.remove(p);
	}

	/**
	 * Disconnects the player after sending the disconnect packet
	 * with the specified reason.
	 *
	 * @param p the player
	 * @param reason the reason
	 */
	public void disconnect(Player p, String reason) {
		p.getSession().write(PacketStore.getKickPacket(reason));
		
		p.getSession().close(false);
		removePlayer(p);
	}
	
	/**
	 * Disconnects the player instantly without a reason.
	 *
	 * @param p the p
	 */
	public void disconnect(Player p) {
		p.getSession().close(true);
		removePlayer(p);
	}
	
	/**
	 * Writes the specified packet to all connected players.
	 *
	 * @param msg the packet to write
	 */
	public void massWrite(Object msg) {
		for (Player p : playerList) {
			if (p != null && p.isConnected()) {
				p.getSession().write(msg);
			}
		}
	}
	
	/**
	 * Loads the ban lists.
	 */
	public void loadBanList() {
		try {
			File banned = new File(MineServ.BANNED_LIST);
			if (banned.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(banned));
				
				String line;
				while ((line = br.readLine()) != null) {
					bannedList.add(line);
				}
				
				br.close();
			} else
					banned.createNewFile();
			
			File ipBanned = new File(MineServ.IPBANNED_LIST);
			if (ipBanned.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(ipBanned));
				
				String line;
				while ((line = br.readLine()) != null) {
					ipBannedList.add(line);
				}
			} else
				ipBanned.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if player name is banned.
	 *
	 * @param playerName the player name
	 * @return true, if is banned
	 */
	public boolean isBanned(String playerName) {
		if (bannedList.contains(playerName))
			return true;
		return false;
	}
	
	/**
	 * Checks if ip address is banned.
	 *
	 * @param ip the ip
	 * @return true, if is banned
	 */
	public boolean isIpBanned(String ip) {
		if (ip.contains(":")) {
			ip = ip.substring(1, ip.indexOf(":"));
		}
		
		if (ipBannedList.contains(ip))
			return true;
		return false;
	}
	
	/**
	 * Adds the player to the ban list.
	 *
	 * @param p the player
	 */
	public void ban(Player p) {
		bannedList.add(p.getPlayerName());
	}
	
	/**
	 * Adds the player's ip address to the ip ban list.
	 *
	 * @param p the player
	 */
	public void ipBan(Player p) {
		String ipString = p.getSession().getRemoteAddress().toString();
		ipBannedList.add(ipString.substring(1, ipString.indexOf(":")));
	}
}
