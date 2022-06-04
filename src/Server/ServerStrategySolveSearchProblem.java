package Server;
import algorithms.mazeGenerators.Maze;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    ConcurrentHashMap<byte[], String> memory;
    AtomicInteger sol_counter;
    AtomicInteger maze_counter;
    String tempDirectoryPath;

    public ServerStrategySolveSearchProblem()
    {
        memory = new ConcurrentHashMap<>();
        sol_counter = new AtomicInteger();
        maze_counter = new AtomicInteger();
        sol_counter.set(0);
        maze_counter.set(0);
        tempDirectoryPath = System.getProperty("java.io.tmpdir");
    }
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            byte[] id = maze.toByteArray();
            if (memory.containsKey(id))
            {
                String sol_name = memory.get(id);
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(tempDirectoryPath+"\\" +sol_name));
                Solution sol =(Solution)in.readObject();
                toClient.writeObject(sol);
                toClient.flush();

            }
            else {
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                ISearchingAlgorithm search = new BreadthFirstSearch();
                Solution sol = search.solve(searchableMaze);
                handleSol(sol, id);
                handleMaze(id, maze);
                toClient.writeObject(sol);
                toClient.flush();
            }
            toClient.close();
            fromClient.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    private synchronized void handleSol(Solution sol, byte[] id)
    {
        memory.put(id, "solution"+sol_counter);
        try {
            FileOutputStream sol_file = new FileOutputStream(tempDirectoryPath +"\\solution"+ sol_counter);
            sol_counter.incrementAndGet();
            ObjectOutputStream out = new ObjectOutputStream(sol_file);
            out.writeObject(sol);
            out.flush();
            out.close();
            sol_file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void  handleMaze(byte[] id, Maze maze){
        try {
            FileOutputStream file = new FileOutputStream(tempDirectoryPath + "\\maze" + maze_counter);
            maze_counter.incrementAndGet();
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(maze);
            out.flush();
            out.close();
            file.close();
        }
        catch(Exception e){
            e.printStackTrace();

        }

    }
    
    
}
