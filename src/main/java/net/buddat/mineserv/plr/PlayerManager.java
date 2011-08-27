package net.buddat.mineserv.plr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import net.buddat.mineserv.MineServ;
import net.buddat.mineserv.net.packet.PacketStore;

import org.apache.mina.core.session.IoSession;

public class PlayerManager {

	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<String> bannedList = new ArrayList<String>();
	private ArrayList<String> ipBannedList = new ArrayList<String>();
	
	public PlayerManager() { }
	
	public void init() {
		loadBanList();
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

	public void disconnect(Player p, String reason) {
		p.getSession().write(PacketStore.getKickPacket(reason));
		
		p.getSession().close(false);
		removePlayer(p);
	}
	
	public void disconnect(Player p) {
		p.getSession().close(true);
		removePlayer(p);
	}
	
	public void massWrite(Object msg) {
		for (Player p : playerList) {
			if (p != null && p.isConnected()) {
				p.getSession().write(msg);
			}
		}
	}
	
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
	
	public boolean isBanned(String playerName) {
		if (bannedList.contains(playerName))
			return true;
		return false;
	}
	
	public boolean isIpBanned(String ip) {
		if (ip.contains(":")) {
			ip = ip.substring(1, ip.indexOf(":"));
		}
		
		if (ipBannedList.contains(ip))
			return true;
		return false;
	}
	
	public void ban(Player p) {
		bannedList.add(p.getPlayerName());
	}
	
	public void ipBan(Player p) {
		String ipString = p.getSession().getRemoteAddress().toString();
		ipBannedList.add(ipString.substring(1, ipString.indexOf(":")));
	}
}
