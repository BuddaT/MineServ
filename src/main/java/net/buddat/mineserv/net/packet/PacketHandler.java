package net.buddat.mineserv.net.packet;

/**
 * The Interface PacketHandler.
 * 
 * @author Budda
 */
public interface PacketHandler {

	/**
	 * Handle packet.
	 *
	 * @param p the ppacket to be handled
	 */
	public void handlePacket(Packet p);
	
}
