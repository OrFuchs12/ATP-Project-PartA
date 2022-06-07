package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * the class of the server. has a port, listening internval, startegy, a boolean stop, and thread pool
 */
public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;


    public Server(int port, int listeningIntervalMS, IServerStrategy strategy)  {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        Configurations co = Configurations.getInstance();
        this.threadPool = Executors.newFixedThreadPool(Integer.parseInt(co.getProp("threadPoolSize")));
    }


    /**
     * creates a thread for the server
     */
    public void start(){
        Thread serv = new Thread(()-> run());
        serv.start();
    }


    /**
     * looks for the clientSocket, creates the thread pool for the clients
     */
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    threadPool.submit(() -> {handleClient(clientSocket);});}
                catch (SocketTimeoutException e){}}
            serverSocket.close();
            threadPool.shutdown();
            }
            catch (IOException e) {}}


    /**
     * calls the strategy of the server
     * @param clientSocket
     */
    private void handleClient(Socket clientSocket) {
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();}
        catch (IOException e){}}


    /**
     * stops the server
     */
    public void stop() {
        stop = true;}
}
