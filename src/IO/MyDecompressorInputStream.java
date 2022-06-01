package IO;

import algorithms.mazeGenerators.Position;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class MyDecompressorInputStream extends InputStream{
    private InputStream in;
    public MyDecompressorInputStream(InputStream b) {in = b;}

    @Override
    public int read(byte[] b) throws IOException {
        byte[] Byte_input = in.readAllBytes();
        if (Byte_input == null){
            return 0;}
        int rows = new BigInteger(Arrays.copyOfRange(Byte_input, 0, 2)).intValue();
        int columns = new BigInteger(Arrays.copyOfRange(Byte_input, 2,4)).intValue();
        for(int i = 0; i<12; i++){
            b[i]= Byte_input[i];}
        int next_index = 12;
        int limit=0;
        int check  = (rows * columns) % 8;
        if (check ==0)
        {
            limit = Byte_input.length;}
        else
        {
            limit = Byte_input.length-1;
        }
        for (int i=12; i< limit; i++)
        {
           int num= (int)Byte_input[i] & 0xff;
           String s = Integer.toBinaryString(num);
           String tmp = "";
           int diff = 8- s.length();
           for (int j=0; j<diff; j++)
           {
               tmp += "0";
           }
           tmp += s;
           s= tmp;
           for (int k=0; k< s.length(); k++)
           {
               int curr =Character.getNumericValue(s.charAt(k));
               b[next_index] =(byte)curr;
               next_index++;
           }
        }

        if (check != 0){
            int last = (int)Byte_input[Byte_input.length-1] & 0xff;
            String s1 = Integer.toBinaryString(last);
            String tmp = "";
            int diff = check- s1.length();
            for (int j=0; j<diff; j++)
            {
                tmp += "0";
            }
            tmp += s1;
            s1= tmp;
            for (int k=0; k< s1.length(); k++)
            {
                int curr =Character.getNumericValue(s1.charAt(k));
                b[next_index] =(byte)curr;
                next_index++;
            }
        }
        return 0;
    }

    @Override
    public int read() throws IOException {

        return 0;
    }
}
