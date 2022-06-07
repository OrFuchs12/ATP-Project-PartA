package Client;

import java.io.InputStream;
import java.io.OutputStream;



public interface IClientStrategy {
    /**
     * applies the client strategy
     * @param inFromServer
     * @param outToServer
     */
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);

}
