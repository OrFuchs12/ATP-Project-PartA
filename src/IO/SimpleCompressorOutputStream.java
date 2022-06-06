package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

public class SimpleCompressorOutputStream extends OutputStream implements Serializable
{
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {}

    /**
     * receives a byteArray as a parameter that contains the details of the maze and compress it to a less memory consuming
     * array with a simple compress algorithm. after the compress write the compressed array into the outputStream.
     * @param b
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException {
        ByteArrayOutputStream sol = new ByteArrayOutputStream();
        int zero_counter = 0;
        int one_counter = 0;

        //write rows, columns, start, end
        for (int i=0; i<12; i++) {
            sol.write(b[i]);}
        //write the maze itself counting the sequence of zeros or ones

        for (int i=12; i<b.length; i++) {
            if (b[i] ==0 && zero_counter !=0 ) {
                zero_counter++;}
            else if (b[i] ==0 && zero_counter ==0 && one_counter ==0){
                zero_counter++;
            }
            else if (b[i] ==0 && zero_counter ==0){
                while (one_counter > 255) {
                    sol.write(255);
                    sol.write(0);
                    one_counter-=255;}
                sol.write(one_counter);
                one_counter=0;
                zero_counter++;}
            else if (b[i] ==1 && one_counter !=0){
                one_counter++;}
            else if (b[i] ==1 && one_counter ==0 && zero_counter ==0){
                sol.write(0);
                one_counter ++;
            }
            else{
                while (zero_counter >255) {
                    sol.write(255);
                    sol.write(0);
                    zero_counter-=255;}
                sol.write(zero_counter);
                zero_counter=0;
                one_counter++;}}
        if (zero_counter !=0) {
            while(zero_counter > 255)
            {
                sol.write(255);
                sol.write(0);
                zero_counter-=255;}
            sol.write(zero_counter);}
        else
        {
            while (one_counter > 255) {
                sol.write(255);
                sol.write(0);
                one_counter-=255;
            }
            sol.write(one_counter);
        }

        //make it the right size
        ByteBuffer buffer = ByteBuffer.allocate(sol.size());
        buffer.put(sol.toByteArray());
        out.write(buffer.array());

    }
}

