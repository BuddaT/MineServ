package net.buddat.mineserv.net.codec;

import net.buddat.mineserv.net.packet.Packet;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MinecraftProtocolDecoder implements ProtocolDecoder {

	private int[] packetLengths;

	public MinecraftProtocolDecoder(int[] pLen) {
		this.packetLengths = pLen;
	}

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		if (in.remaining() >= 1) {
			int id = in.get();
			int len = packetLengths[id];
			
			if (len == -1) { //variable length
				len = in.remaining();
			}
			
			if (len < 0) {
				return;
			}
			
			if (in.remaining() >= len) {
				byte[] incoming = new byte[len];
				in.get(incoming);
				
				Packet p = new Packet(session, id, incoming);
				out.write(p);
			} else { 
				in.rewind();
			}
		}
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
