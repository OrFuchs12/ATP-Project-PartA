package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ObjectInputStream maze = (ObjectInputStream) fromClient.readObject();

            SearchableMaze searchableMaze = new SearchableMaze(maze);
            //todo which algorithm to solve with configuration
            ISearchingAlgorithm search = new BreadthFirstSearch();
            Solution sol = search.solve(searchableMaze);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputStream os = new MyCompressorOutputStream(out);
            //os.write(sol.toByteArray());
            os.flush();
            toClient.write(out.toByteArray());
            toClient.flush();
            out.close();
            os.close();
            fromClient.close();
            toClient.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
