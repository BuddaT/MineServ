package net.buddat.mineserv.net.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Used by MINA to get the encoder and decoder classes for the Minecraft Protocol.
 * 
 * @author Budda
 */
public class MinecraftCodecFactory implements ProtocolCodecFactory {
	
	/** The encoder. */
	private static ProtocolEncoder encoder = new MinecraftProtocolEncoder();
	
	/** The decoder. */
	private static ProtocolDecoder decoder = new MinecraftProtocolDecoder();

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.codec.ProtocolCodecFactory#getDecoder(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	/* (non-Javadoc)
	 * @see org.apache.mina.filter.codec.ProtocolCodecFactory#getEncoder(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

}
