package net.buddat.mineserv;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.buddat.mineserv.net.packet.Packet;
import net.buddat.mineserv.net.packet.PacketHandler;
import net.buddat.mineserv.net.packet.PacketType;
import net.buddat.mineserv.net.packet.handlers.*;
import net.buddat.mineserv.plr.PlayerManager;

/**
 * Contains the main loop for updating and processing incoming packets.
 * 
 * @author Budda
 */
public class Engine {

	/** The default logger for this class. */
	private final Logger logger = LoggerFactory.getLogger(Engine.class);
	
	/** PlayerManager instance. */
	private PlayerManager playerManager;
	
	/** Incoming packet queue to be processed at next tick. */
	private ArrayList<Packet> packetQueue = new ArrayList<Packet>();
	
	/** PacketHandlers bound to each packet id. */
	private PacketHandler[] packetHandlers = new PacketHandler[256];
	
	/** True if the engine is running. */
	private boolean running = true;
	
	/**
	 * Instantiates a new Engine.
	 * Creates a new instance of PlayerManager.
	 */
	public Engine() {
		playerManager = new PlayerManager();
	}
	
	/**
	 * Called when the engine starts - after connection handling has been
	 * set up in {@link MineServ}.
	 */
	public void start() {
		init();
		run();
	}

	/**
	 * Called before the main loop starts. Initiates the Engine.
	 */
	private void init() {
		bindPacketHandlers();
		playerManager.init();
	}

	/**
	 * Main loop. To be run between 5-20 times per second.
	 */
	private void run() {
		while(running) {
			processIncomingPackets();
			playerManager.tick();
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Passes off Packets from {@link #packetQueue} to the corresponding
	 * {@link PacketHandler} as bound by {@link #bindPacketHandlers()}.
	 */
	private void processIncomingPackets() {
		for (Packet p : getPacketQueue()) {
			PacketHandler ph = packetHandlers[p.getPacketId()];
			if (ph != null) {
				ph.handlePacket(p);
			} else {
				logger.error("Unhandled Packet: {}", p.getPacketId());
			}
		}
		
		getPacketQueue().clear();
	}

	/**
	 * Checks if the Engine is running.
	 *
	 * @return true, if running
	 */
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * Sets whether the Engine is running
	 *
	 * @param r whether engine is running
	 */
	public void setRunning(boolean r) {
		running = r;
	}

	/**
	 * Gets the player manager.
	 *
	 * @return the player manager
	 */
	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	/**
	 * Sets the player manager.
	 *
	 * @param playerManager the new PlayerManager
	 */
	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}
	
	/**
	 * Gets the packet queue.
	 *
	 * @return the packet queue
	 */
	public ArrayList<Packet> getPacketQueue() {
		return packetQueue;
	}

	/**
	 * Sets the packet queue.
	 *
	 * @param packetQueue the new packet queue
	 */
	public void setPacketQueue(ArrayList<Packet> packetQueue) {
		this.packetQueue = packetQueue;
	}

	/**
	 * Binds certain PacketHandler to corresponding packet id as described
	 * in {@link PacketType}.
	 */
	private void bindPacketHandlers() {
		int[] packets = {
				PacketType.LOGIN_REQUEST_RESPONSE.getId(),
				PacketType.HANDSHAKE.getId(),
				PacketType.CHAT.getId()
		};
		
		PacketHandler[] handlers = {
				new LoginHandler(),
				new HandshakeHandler(),
				new ChatHandler()
		};
		
		for (int i = 0; i < packets.length; i++) {
			this.packetHandlers[packets[i]] = handlers[i];
		}
	}
	
}
