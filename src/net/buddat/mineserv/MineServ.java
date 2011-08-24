package net.buddat.mineserv;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.buddat.mineserv.net.ConnectionHandler;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MineServ {
	
	public static final int PORT = 25565;
	
	private static MineServ mineservInstance;
	
	private IoAcceptor connectionAcceptor;
	private IoHandler serverIoHandler;
	
	private Engine serverEngine;

	public static void main(String[] args) {
		new MineServ();
	}
	
	public MineServ() {
		mineservInstance = this;
		
		serverEngine = new Engine();
		
		connectionAcceptor = new NioSocketAcceptor();
		serverIoHandler = new ConnectionHandler();
		
		connectionAcceptor.setHandler(serverIoHandler);
		
		try {
			connectionAcceptor.bind(new InetSocketAddress(PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MineServ getInstance() {
		return mineservInstance;
	}

	public Engine getEngine() {
		return serverEngine;
	}

}
