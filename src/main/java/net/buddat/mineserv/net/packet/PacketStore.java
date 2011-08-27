package net.buddat.mineserv.net.packet;

public class PacketStore {
	
	public static String UTF16 = "UTF-16BE";
	public static String UTF8 = "UTF-8";

	public static Packet getKickPacket(String reason) {
		int packetLength = 2 + (reason.length() * 2);
		Packet packet = new PacketBuilder(packetLength).setId(255).addString(reason, UTF16).toPacket();
		
		return packet;
	}
}
