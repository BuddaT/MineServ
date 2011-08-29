package net.buddat.mineserv.net;

import java.util.ArrayList;

import net.buddat.mineserv.Engine;
import net.buddat.mineserv.MineServ;
import net.buddat.mineserv.net.packet.Packet;
import net.buddat.mineserv.plr.Player;
import net.buddat.mineserv.plr.PlayerManager;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles connections and incoming/outgoing messages.
 * 
 * @author Budda
 */
public class ConnectionHandler implements IoHandler {
	
	/** The default logger for this class */
	private final Logger logger = LoggerFactory.getLogger(ConnectionHandler.class);
	
	/** The server engine. */
	private Engine serverEngine;
	
	/** @see Engine#getPlayerManager() */
	private PlayerManager plrManager;
	
	/** @see Engine#getPacketQueue() */
	private ArrayList<Packet> packetQueue;
	
	/**
	 * Instantiates a new connection handler.
	 */
	public ConnectionHandler() {
		serverEngine = MineServ.getInstance().getEngine();
		plrManager = serverEngine.getPlayerManager();
		
		packetQueue = serverEngine.getPacketQueue();
	}

	/**
	 * Called when an exception is caught in a connection.
	 * Not currently used.
	 * 
	 * @see org.apache.mina.core.service.IoHandler#exceptionCaught(org.apache.mina.core.session.IoSession, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable arg1)
			throws Exception {
		
	}

	/**
	 * Called when a message is recieved from a session.
	 * The message is casted to a Packet and added to {@link #packetQueue} for {@link #serverEngine} to process.
	 * 
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object msg) throws Exception {
		Packet p = (Packet) msg;
		
		packetQueue.add(p);
	}

	/**
	 * Called when the server sends a message to a session.
	 * Not currently used.
	 * 
	 * @see org.apache.mina.core.service.IoHandler#messageSent(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageSent(IoSession session, Object msg) throws Exception {
		
	}

	/**
	 * Called when a session is closed.
	 * Removed the Player instance from {@link #plrManager}.
	 * 
	 * @see org.apache.mina.core.service.IoHandler#sessionClosed(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		Player p = plrManager.getPlayer(session);
		
		if (p != null) {
			plrManager.removePlayer(p);
		}
	}

	/**
	 * Called when a session is created.
	 * Creates a new player and binds it to the IoSession.
	 * 
	 * @see org.apache.mina.core.service.IoHandler#sessionCreated(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {		
		Player newPlayer = new Player(session);
		plrManager.addPlayer(newPlayer);
		
		session.setAttribute("PLAYER", newPlayer);
	}

	/**
	 * Called when a session is marked as idle.
	 * Not currently used.
	 * 
	 * @see org.apache.mina.core.service.IoHandler#sessionIdle(org.apache.mina.core.session.IoSession, org.apache.mina.core.session.IdleStatus)
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus arg1) throws Exception {
		
	}

	/**
	 * Called when a session is opened.
	 * Not currently used.
	 * 
	 * @see org.apache.mina.core.service.IoHandler#sessionOpened(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		
	}

}
