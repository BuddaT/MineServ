package net.buddat.mineserv.net.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MinecraftCodecFactory implements ProtocolCodecFactory {
	
	private static ProtocolEncoder encoder = new MinecraftProtocolEncoder();
	private static ProtocolDecoder decoder = new MinecraftProtocolDecoder();

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

}
