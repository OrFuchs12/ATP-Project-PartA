package Server;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    //ConcurrentHashMap<byte[], String> memory;
    //AtomicInteger sol_counter;
    //AtomicInteger maze_counter;
    String tempDirectoryPath;

    public ServerStrategySolveSearchProblem()
    {
        //memory = new ConcurrentHashMap<>();
        //sol_counter = new AtomicInteger();
        //maze_counter = new AtomicInteger();
        //sol_counter.set(0);
        //maze_counter.set(0);
        tempDirectoryPath = System.getProperty("java.io.tmpdir");
    }
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            byte[] id = maze.toByteArray();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputStream os = new MyCompressorOutputStream(out);
            os.write(id);
            os.flush();

            String sol_name= "";
            for (int i=0; i< out.toByteArray().length; i++)
            {
                int num = (int)out.toByteArray()[i];
                sol_name+=Integer.toString(num);
            }


            if (new File(tempDirectoryPath, "maze"+sol_name).exists())
            {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(tempDirectoryPath+"\\solution" +sol_name));
                Solution sol =(Solution)in.readObject();
                toClient.writeObject(sol);
                toClient.flush();
                System.out.println("solution already exists");

            }
            else {
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                Configurations co = Configurations.getInstance();
                String alg = co.getProp("mazeSearchingAlgorithm");
                ISearchingAlgorithm search = getAlg(alg);
                Solution sol = search.solve(searchableMaze);
                handleSol(sol, sol_name);
                handleMaze(sol_name, maze);
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


    private synchronized void handleSol(Solution sol, String sol_name)
    {
        //memory.put(id, "solution"+sol_counter);
        try {
            FileOutputStream sol_file = new FileOutputStream(tempDirectoryPath +"\\solution"+ sol_name);
            //sol_counter.incrementAndGet();
            ObjectOutputStream out = new ObjectOutputStream(sol_file);
            out.writeObject(sol);
            out.flush();
            out.close();
            sol_file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void  handleMaze(String sol_name, Maze maze){
        try {
            FileOutputStream file = new FileOutputStream(tempDirectoryPath + "\\maze" + sol_name);
            //maze_counter.incrementAndGet();
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

    private ISearchingAlgorithm getAlg(String s)
    {
        if (s.equals("BreadthFirstSearch"))
        {
            return new BreadthFirstSearch();
        }
        if (s.equals("DepthFirstSearch"))
        {
            return new DepthFirstSearch();
        }
        if (s.equals("BestFirstSearch"))
        {
            return new BestFirstSearch();
        }
        return null;
    }
    
    
}
