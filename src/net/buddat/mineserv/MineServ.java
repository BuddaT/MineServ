package net.buddat.mineserv;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.buddat.mineserv.net.ConnectionHandler;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

// TODO: Auto-generated Javadoc
/**
 * The Class MineServ.
 */
public class MineServ {
	
	/** The Constant PORT. */
	public static final int PORT = 25565;
	
	/** The mineserv instance. */
	private static MineServ mineservInstance;
	
	/** The connection acceptor. */
	private IoAcceptor connectionAcceptor;
	
	/** The server io handler. */
	private IoHandler serverIoHandler;
	
	/** The server engine. */
	private Engine serverEngine;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new MineServ();
	}
	
	/**
	 * Instantiates a new mine serv.
	 */
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
	
	/**
	 * Gets the single instance of MineServ.
	 *
	 * @return single instance of MineServ
	 */
	public static MineServ getInstance() {
		return mineservInstance;
	}

	/**
	 * Gets the engine.
	 *
	 * @return the engine
	 */
	public Engine getEngine() {
		return serverEngine;
	}

}
