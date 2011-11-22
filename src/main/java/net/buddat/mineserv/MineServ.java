package net.buddat.mineserv;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.buddat.mineserv.net.ConnectionHandler;
import net.buddat.mineserv.net.codec.MinecraftCodecFactory;
import net.buddat.mineserv.options.Options;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entry point for program, sets up Engine and connection handling.
 * 
 * @author Budda
 */
public class MineServ {

	/** Path to the options file. */
	public static final String OPTIONS_FILE = "config.ini";
	
	/** Path and filenames of ban and ip-ban lists. */
	public static final String BANNED_LIST = "banned.txt", IPBANNED_LIST = "banned-ip.txt";

	/** The MineServ instance. */
	private static MineServ mineservInstance;

	/** The connection acceptor. */
	private final IoAcceptor connectionAcceptor;

	/** The connection handler. */
	private final IoHandler serverIoHandler;

	/** The server engine. */
	private final Engine serverEngine;
	
	/** The server options. */
	private final Options serverOptions;

	/**
	 * Default logger for this class.
	 */
	private final Logger logger = LoggerFactory.getLogger(MineServ.class);

	/**
	 * Entry point. Creates a new instance of MineServ.
	 *
	 * @param args N/A
	 */
	public static void main(String[] args) {
		new MineServ();
	}

	/**
	 * Sets up Engine and connection details, binds to {@link #PORT}
	 * and starts the {@link #serverEngine}.
	 */
	public MineServ() {
		mineservInstance = this;
		
		serverOptions = new Options(OPTIONS_FILE);

		serverEngine = new Engine();

		connectionAcceptor = new NioSocketAcceptor();
		serverIoHandler = new ConnectionHandler();

		connectionAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
		connectionAcceptor.getFilterChain().addLast("protocolFilter", new ProtocolCodecFilter(new MinecraftCodecFactory()));

		connectionAcceptor.setHandler(serverIoHandler);

		try {
			int port = (Integer) getOptions().getOption(Options.PORT).getValue();
			
			connectionAcceptor.bind(new InetSocketAddress(port));
			logger.info("Bound to port: {}", port);
		} catch (IOException e) {
			e.printStackTrace();
		}

		serverEngine.start();
	}

	/**
	 * Gets the single instance of MineServ.
	 *
	 * @return MineServ instance.
	 */
	public static MineServ getInstance() {
		return mineservInstance;
	}

	/**
	 * Gets the engine.
	 *
	 * @return serverEngine
	 */
	public Engine getEngine() {
		return serverEngine;
	}
	
	/**
	 * Gets the options for the server.
	 * 
	 * @return the options
	 */
	public Options getOptions() {
		return serverOptions;
	}
	
}
