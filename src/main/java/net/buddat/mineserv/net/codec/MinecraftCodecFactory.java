package net.buddat.mineserv.net.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MinecraftCodecFactory implements ProtocolCodecFactory {
	
	private static ProtocolEncoder encoder;
	private static ProtocolDecoder decoder;

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}
	
	static {
		final int[] packetLengthsIn = new int[256];
		
		packetLengthsIn[0] = 1;
		packetLengthsIn[1] = -1;
		packetLengthsIn[2] = -1;
		packetLengthsIn[3] = -1;
		packetLengthsIn[4] = 9;
		packetLengthsIn[5] = 11;
		packetLengthsIn[6] = 13;
		packetLengthsIn[7] = 10;
		packetLengthsIn[8] = 3;
		packetLengthsIn[9] = 2;
		packetLengthsIn[10] = 2;
		packetLengthsIn[11] = 34;
		packetLengthsIn[12] = 10;
		packetLengthsIn[13] = 42;
		packetLengthsIn[14] = 12;
		packetLengthsIn[15] = -1;
		packetLengthsIn[16] = 3;
		packetLengthsIn[17] = 15;
		packetLengthsIn[18] = 6;
		packetLengthsIn[19] = 6;
		packetLengthsIn[20] = -1;
		packetLengthsIn[21] = 25;
		packetLengthsIn[22] = 9;
		packetLengthsIn[23] = -1;
		packetLengthsIn[24] = -1;
		packetLengthsIn[25] = -1;
		packetLengthsIn[26] = 0;
		packetLengthsIn[27] = 11;
		packetLengthsIn[28] = 5;
		packetLengthsIn[29] = 5;
		packetLengthsIn[30] = 8;
		packetLengthsIn[31] = 7;
		packetLengthsIn[32] = 10;
		packetLengthsIn[33] = 19;
		packetLengthsIn[34] = 0;
		packetLengthsIn[35] = 0;
		packetLengthsIn[36] = 0;
		packetLengthsIn[37] = 0;
		packetLengthsIn[38] = 6;
		packetLengthsIn[39] = 9;
		packetLengthsIn[40] = -1;
		packetLengthsIn[41] = 0;
		packetLengthsIn[42] = 0;
		packetLengthsIn[43] = 0;
		packetLengthsIn[44] = 0;
		packetLengthsIn[45] = 0;
		packetLengthsIn[46] = 0;
		packetLengthsIn[47] = 0;
		packetLengthsIn[48] = 0;
		packetLengthsIn[49] = 0;
		packetLengthsIn[50] = 10;
		packetLengthsIn[51] = -1;
		packetLengthsIn[52] = -1;
		packetLengthsIn[53] = 12;
		packetLengthsIn[54] = 13;
		packetLengthsIn[55] = 0;
		packetLengthsIn[56] = 0;
		packetLengthsIn[57] = 0;
		packetLengthsIn[58] = 0;
		packetLengthsIn[59] = 0;
		packetLengthsIn[60] = -1;
		packetLengthsIn[61] = 18;
		packetLengthsIn[62] = 0;
		packetLengthsIn[63] = 0;
		packetLengthsIn[64] = 0;
		packetLengthsIn[65] = 0;
		packetLengthsIn[66] = 0;
		packetLengthsIn[67] = 0;
		packetLengthsIn[68] = 0;
		packetLengthsIn[69] = 0;
		packetLengthsIn[70] = 2;
		packetLengthsIn[71] = 18;
		packetLengthsIn[72] = 0;
		packetLengthsIn[73] = 0;
		packetLengthsIn[74] = 0;
		packetLengthsIn[75] = 0;
		packetLengthsIn[76] = 0;
		packetLengthsIn[77] = 0;
		packetLengthsIn[78] = 0;
		packetLengthsIn[79] = 0;
		packetLengthsIn[80] = 0;
		packetLengthsIn[81] = 0;
		packetLengthsIn[82] = 0;
		packetLengthsIn[83] = 0;
		packetLengthsIn[84] = 0;
		packetLengthsIn[85] = 0;
		packetLengthsIn[86] = 0;
		packetLengthsIn[87] = 0;
		packetLengthsIn[88] = 0;
		packetLengthsIn[89] = 0;
		packetLengthsIn[90] = 0;
		packetLengthsIn[91] = 0;
		packetLengthsIn[92] = 0;
		packetLengthsIn[93] = 0;
		packetLengthsIn[94] = 0;
		packetLengthsIn[95] = 0;
		packetLengthsIn[96] = 0;
		packetLengthsIn[97] = 0;
		packetLengthsIn[98] = 0;
		packetLengthsIn[99] = 0;
		packetLengthsIn[100] = -1;
		packetLengthsIn[101] = 2;
		packetLengthsIn[102] = -1;
		packetLengthsIn[103] = -1;
		packetLengthsIn[104] = -1;
		packetLengthsIn[105] = 6;
		packetLengthsIn[106] = 5;
		packetLengthsIn[107] = 0;
		packetLengthsIn[108] = 0;
		packetLengthsIn[109] = 0;
		packetLengthsIn[110] = 0;
		packetLengthsIn[111] = 0;
		packetLengthsIn[112] = 0;
		packetLengthsIn[113] = 0;
		packetLengthsIn[114] = 0;
		packetLengthsIn[115] = 0;
		packetLengthsIn[116] = 0;
		packetLengthsIn[117] = 0;
		packetLengthsIn[118] = 0;
		packetLengthsIn[119] = 0;
		packetLengthsIn[120] = 0;
		packetLengthsIn[121] = 0;
		packetLengthsIn[122] = 0;
		packetLengthsIn[123] = 0;
		packetLengthsIn[124] = 0;
		packetLengthsIn[125] = 0;
		packetLengthsIn[126] = 0;
		packetLengthsIn[127] = 0;
		packetLengthsIn[128] = 0;
		packetLengthsIn[129] = 0;
		packetLengthsIn[130] = -1;
		packetLengthsIn[131] = -1;
		packetLengthsIn[132] = 0;
		packetLengthsIn[133] = 0;
		packetLengthsIn[134] = 0;
		packetLengthsIn[135] = 0;
		packetLengthsIn[136] = 0;
		packetLengthsIn[137] = 0;
		packetLengthsIn[138] = 0;
		packetLengthsIn[139] = 0;
		packetLengthsIn[140] = 0;
		packetLengthsIn[141] = 0;
		packetLengthsIn[142] = 0;
		packetLengthsIn[143] = 0;
		packetLengthsIn[144] = 0;
		packetLengthsIn[145] = 0;
		packetLengthsIn[146] = 0;
		packetLengthsIn[147] = 0;
		packetLengthsIn[148] = 0;
		packetLengthsIn[149] = 0;
		packetLengthsIn[150] = 0;
		packetLengthsIn[151] = 0;
		packetLengthsIn[152] = 0;
		packetLengthsIn[153] = 0;
		packetLengthsIn[154] = 0;
		packetLengthsIn[155] = 0;
		packetLengthsIn[156] = 0;
		packetLengthsIn[157] = 0;
		packetLengthsIn[158] = 0;
		packetLengthsIn[159] = 0;
		packetLengthsIn[160] = 0;
		packetLengthsIn[161] = 0;
		packetLengthsIn[162] = 0;
		packetLengthsIn[163] = 0;
		packetLengthsIn[164] = 0;
		packetLengthsIn[165] = 0;
		packetLengthsIn[166] = 0;
		packetLengthsIn[167] = 0;
		packetLengthsIn[168] = 0;
		packetLengthsIn[169] = 0;
		packetLengthsIn[170] = 0;
		packetLengthsIn[171] = 0;
		packetLengthsIn[172] = 0;
		packetLengthsIn[173] = 0;
		packetLengthsIn[174] = 0;
		packetLengthsIn[175] = 0;
		packetLengthsIn[176] = 0;
		packetLengthsIn[177] = 0;
		packetLengthsIn[178] = 0;
		packetLengthsIn[179] = 0;
		packetLengthsIn[180] = 0;
		packetLengthsIn[181] = 0;
		packetLengthsIn[182] = 0;
		packetLengthsIn[183] = 0;
		packetLengthsIn[184] = 0;
		packetLengthsIn[185] = 0;
		packetLengthsIn[186] = 0;
		packetLengthsIn[187] = 0;
		packetLengthsIn[188] = 0;
		packetLengthsIn[189] = 0;
		packetLengthsIn[190] = 0;
		packetLengthsIn[191] = 0;
		packetLengthsIn[192] = 0;
		packetLengthsIn[193] = 0;
		packetLengthsIn[194] = 0;
		packetLengthsIn[195] = 0;
		packetLengthsIn[196] = 0;
		packetLengthsIn[197] = 0;
		packetLengthsIn[198] = 0;
		packetLengthsIn[199] = 0;
		packetLengthsIn[200] = 6;
		packetLengthsIn[201] = 0;
		packetLengthsIn[202] = 0;
		packetLengthsIn[203] = 0;
		packetLengthsIn[204] = 0;
		packetLengthsIn[205] = 0;
		packetLengthsIn[206] = 0;
		packetLengthsIn[207] = 0;
		packetLengthsIn[208] = 0;
		packetLengthsIn[209] = 0;
		packetLengthsIn[210] = 0;
		packetLengthsIn[211] = 0;
		packetLengthsIn[212] = 0;
		packetLengthsIn[213] = 0;
		packetLengthsIn[214] = 0;
		packetLengthsIn[215] = 0;
		packetLengthsIn[216] = 0;
		packetLengthsIn[217] = 0;
		packetLengthsIn[218] = 0;
		packetLengthsIn[219] = 0;
		packetLengthsIn[220] = 0;
		packetLengthsIn[221] = 0;
		packetLengthsIn[222] = 0;
		packetLengthsIn[223] = 0;
		packetLengthsIn[224] = 0;
		packetLengthsIn[225] = 0;
		packetLengthsIn[226] = 0;
		packetLengthsIn[227] = 0;
		packetLengthsIn[228] = 0;
		packetLengthsIn[229] = 0;
		packetLengthsIn[230] = 0;
		packetLengthsIn[231] = 0;
		packetLengthsIn[232] = 0;
		packetLengthsIn[233] = 0;
		packetLengthsIn[234] = 0;
		packetLengthsIn[235] = 0;
		packetLengthsIn[236] = 0;
		packetLengthsIn[237] = 0;
		packetLengthsIn[238] = 0;
		packetLengthsIn[239] = 0;
		packetLengthsIn[240] = 0;
		packetLengthsIn[241] = 0;
		packetLengthsIn[242] = 0;
		packetLengthsIn[243] = 0;
		packetLengthsIn[244] = 0;
		packetLengthsIn[245] = 0;
		packetLengthsIn[246] = 0;
		packetLengthsIn[247] = 0;
		packetLengthsIn[248] = 0;
		packetLengthsIn[249] = 0;
		packetLengthsIn[250] = 0;
		packetLengthsIn[251] = 0;
		packetLengthsIn[252] = 0;
		packetLengthsIn[253] = 0;
		packetLengthsIn[254] = 0;
		packetLengthsIn[255] = -1;
		
		encoder = new MinecraftProtocolEncoder(packetLengthsIn);
		decoder = new MinecraftProtocolDecoder(packetLengthsIn);
	}

}
