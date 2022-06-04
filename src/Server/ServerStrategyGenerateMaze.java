package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] data = (int[])fromClient.readObject();
            IMazeGenerator mg = new MyMazeGenerator();
            Maze maze = mg.generate(data[1], data[0]);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //byte[] out1 = new byte[data[1] * data[0] +12];
            OutputStream os = new SimpleCompressorOutputStream(out);
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
}
