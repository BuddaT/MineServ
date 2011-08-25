package net.buddat.mineserv.net.codec;

import net.buddat.mineserv.net.packet.Packet;
import net.buddat.mineserv.util.Logger;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinecraftProtocolEncoder implements ProtocolEncoder {

	private final int[] packetLengths;

	private final Logger logger = LoggerFactory.getLogger(MinecraftProtocolEncoder.class);

	public MinecraftProtocolEncoder(int[] pLen) {
		this.packetLengths = pLen;
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		Packet p = (Packet) message;
		IoBuffer buffer;

		byte[] b = p.getPayload();
		int id = p.getPacketId();

		buffer = IoBuffer.allocate(b.length + 1);
		logger.debug("Buffer allocated {} bytes for packet id {}", b.length + 1, id);

		buffer.put((byte) id);
		buffer.put(b);
		buffer.flip();

		out.write(buffer);
		Logger.log("Encoded and written: " + new String(buffer.array()));
	}

}
