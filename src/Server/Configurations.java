package Server;

import java.io.*;
import java.util.Properties;

public class Configurations {

    private static Configurations inst;
    private static Properties prop;

    public static Configurations getInstance(){
        if (inst == null)
        {
            inst = new Configurations();
        }
        return inst;
    }

    private Configurations()
    {
        prop = new Properties();
    }

    public String getProp(String p)  {
        try {
            FileInputStream file = new FileInputStream("resources/config.properties");
            prop.load(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return prop.getProperty(p);
    }

    public void setProp(String k, String v) throws IOException {
        OutputStream file = new FileOutputStream("resources/config.properties");
        prop.setProperty(k, v);
        prop.store(file, "stored");
    }


}
