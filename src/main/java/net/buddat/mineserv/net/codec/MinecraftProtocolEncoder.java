package net.buddat.mineserv.net.codec;

import net.buddat.mineserv.net.packet.Packet;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Encodes packets into byte buffers which is then passed on to MINA
 * to write to the session.
 * Only access directly by MINA.
 * 
 * @author Budda
 */
public class MinecraftProtocolEncoder implements ProtocolEncoder {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(MinecraftProtocolEncoder.class);

	/**
	 * Instantiates a new minecraft protocol encoder.
	 */
	public MinecraftProtocolEncoder() { }

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.codec.ProtocolEncoder#dispose(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void dispose(IoSession session) throws Exception {
		
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.codec.ProtocolEncoder#encode(org.apache.mina.core.session.IoSession, java.lang.Object, org.apache.mina.filter.codec.ProtocolEncoderOutput)
	 */
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
		logger.debug("Encoded and written: {}", buffer.array());
	}

}
