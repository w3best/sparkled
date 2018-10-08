package io.sparkled.udpserver;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UdpServerImpl implements UdpServer {

    private static final Logger logger = LoggerFactory.getLogger(UdpServerImpl.class);
    private static final int THREAD_COUNT = 4;
    private static final int RECEIVE_BUFFER_SIZE = 16;
    private final RequestHandler requestHandler;
    private final ExecutorService executor;
    private boolean started;

    @Inject
    public UdpServerImpl(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.executor = Executors.newFixedThreadPool(THREAD_COUNT,
                new ThreadFactoryBuilder().setNameFormat("udp-server-%d").build()
        );
    }

    @Override
    public void start(int port) throws SocketException {
        if (started) {
            logger.warn("Attempted to start UDP server, but it is already running.");
            return;
        }

        DatagramSocket serverSocket = new DatagramSocket(port);

        executor.submit(() -> {
            byte[] receiveData = new byte[RECEIVE_BUFFER_SIZE];

            while (true) {
                try {
                    handleRequest(serverSocket, receiveData);
                } catch (Exception e) {
                    logger.error("Failed to handle UDP request.", e);
                }
            }
        });

        started = true;
        logger.info("Started UDP server at port {}.", port);
    }

    private void handleRequest(final DatagramSocket serverSocket, final byte[] receiveData) throws IOException {
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        requestHandler.handle(serverSocket, receivePacket);
    }
}
