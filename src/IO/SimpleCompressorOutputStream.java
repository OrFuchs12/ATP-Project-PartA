package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {}

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
                while (one_counter > 127) {
                    sol.write(127);
                    sol.write(0);
                    one_counter-=127;}
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

                while (zero_counter >127) {
                    sol.write(127);
                    sol.write(0);
                    zero_counter-=127;}
                sol.write(zero_counter);
                zero_counter=0;
                one_counter++;}}
        if (zero_counter !=0) { sol.write(zero_counter);}
        else {sol.write(one_counter);}

        //make it the right size
        ByteBuffer buffer = ByteBuffer.allocate(sol.size());
        buffer.put(sol.toByteArray());
        out.write(buffer.array());

    }
}

