package net.buddat.mineserv;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.buddat.mineserv.net.ConnectionHandler;
import net.buddat.mineserv.net.codec.MinecraftCodecFactory;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class MineServ.
 */
public class MineServ {

	/** The Constant PORT. */
	public static final int PORT = 25565;
	public static final String BANNED_LIST = "banned.txt", IPBANNED_LIST = "banned-ip.txt";

	/** The mineserv instance. */
	private static MineServ mineservInstance;

	/** The connection acceptor. */
	private final IoAcceptor connectionAcceptor;

	/** The server io handler. */
	private final IoHandler serverIoHandler;

	/** The server engine. */
	private final Engine serverEngine;

	/**
	 * Default logger for this class.
	 */
	private final Logger logger = LoggerFactory.getLogger(MineServ.class);

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

		connectionAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
		connectionAcceptor.getFilterChain().addLast("protocolFilter", new ProtocolCodecFilter(new MinecraftCodecFactory()));

		connectionAcceptor.setHandler(serverIoHandler);

		try {
			connectionAcceptor.bind(new InetSocketAddress(PORT));
			logger.info("Bound to port: {}", PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		serverEngine.start();
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
