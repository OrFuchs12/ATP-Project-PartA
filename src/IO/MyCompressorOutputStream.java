package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Base64;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {}

    @Override
    public void write(byte[] b) throws IOException {
        ByteArrayOutputStream sol = new ByteArrayOutputStream();

        //write rows, columns, start, end
        for (int i=0; i<12; i++) {
            sol.write(b[i]);}
        //write the maze itself counting turning the ones and zeros from a binary number to int

        int next_index =12;
        while (next_index < b.length-8)
        {
            byte[] curr = Arrays.copyOfRange(b, next_index, next_index +8);
            int[] counter= new int[8];
            String s = "";
            for (int i=0; i<8; i++)
            {
                int num = curr[i];
                counter[i] = num;
                s+= Integer.toString(num);
            }
            int final_num = Integer.parseUnsignedInt(s, 2);
            sol.write(final_num);
            next_index +=8;
        }
        if (next_index <= b.length -1)
        {
            byte[] curr = Arrays.copyOfRange(b, next_index, b.length);
            int[] counter= new int[8];
            String s = "";
            for (int i=0; i<curr.length; i++)
            {
                int num = curr[i];
                counter[i] = num;
                s+= Integer.toString(num);
            }
            int final_num = Integer.parseUnsignedInt(s, 2);
            sol.write(final_num);
        }

        //make it the right size
        ByteBuffer buffer = ByteBuffer.allocate(sol.size());
        buffer.put(sol.toByteArray());
        out.write(buffer.array());

    }
}
