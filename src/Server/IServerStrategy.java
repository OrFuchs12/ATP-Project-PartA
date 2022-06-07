package Server;

import java.io.InputStream;
import java.io.OutputStream;


/**
 * interface for a strategy of the server
 */
public interface IServerStrategy {
        /**
         * the strategy the server does on the input stream and sends to the output stream
         * @param inFromClient
         * @param outToClient
         */
        void ServerStrategy(InputStream inFromClient, OutputStream outToClient);
}
