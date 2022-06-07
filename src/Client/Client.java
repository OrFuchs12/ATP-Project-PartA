package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * CLient: serverIP, serverPort, strategy
 */
public class Client {
     private InetAddress serverIP;
        private int serverPort;
        private IClientStrategy strategy;

        public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
            this.serverIP = serverIP;
            this.serverPort = serverPort;
            this.strategy = strategy;
        }

    /**
     * creates a new client socket, and applies its strategy
     */
    public void start(){
            try
            {
                Socket serverSocket = new Socket(serverIP, serverPort);
                System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
                strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
                serverSocket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    /**
     * calls the start of the client
     */
    public void communicateWithServer() {
            start();
    }
}

