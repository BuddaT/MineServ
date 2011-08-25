package net.buddat.mineserv.net.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MinecraftProtocolEncoder implements ProtocolEncoder {

	private int[] packetLengths;

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
		// TODO Auto-generated method stub

	}

}
