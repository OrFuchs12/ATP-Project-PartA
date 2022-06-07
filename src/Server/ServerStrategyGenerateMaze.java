package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.ArrayList;



public class ServerStrategyGenerateMaze implements IServerStrategy{


    /**
     * the client sends data for the server that generates a maze and sends it back
     * @param inFromClient
     * @param outToClient
     */
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] data = (int[])fromClient.readObject();
            Configurations co = Configurations.getInstance();
            String gen = co.getProp("mazeGeneratingAlgorithm");
            IMazeGenerator final_gen = getGen(gen);
            Maze maze = final_gen.generate(data[1], data[0]);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputStream os = new MyCompressorOutputStream(out);
            os.write(maze.toByteArray());
            os.flush();
            toClient.writeObject(out.toByteArray());
            toClient.flush();
            out.close();
            os.close();
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * return the configurations according to the string
     * @param s
     * @return the relevent maze generator
     */
    private IMazeGenerator getGen( String s)
    {
        if (s.equals("EmptyMazeGenerator"))
        {
            return new EmptyMazeGenerator();
        }
        if (s.equals("SimpleMazeGenerator"))
        {
            return new SimpleMazeGenerator();
        }
        if (s.equals("MyMazeGenerator"))
        {
            return new MyMazeGenerator();
        }
        return null;
    }
}
