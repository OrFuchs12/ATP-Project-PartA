package IO;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;

public class SimpleDecompressorInputStream extends InputStream{
    private InputStream in;
    public SimpleDecompressorInputStream(InputStream b) {in = b;}

    @Override
    public int read(byte[] b) throws IOException {

        byte[] Byte_input = in.readAllBytes();
        int rows = new BigInteger(Arrays.copyOfRange(Byte_input, 0, 2)).intValue();
        int columns = new BigInteger(Arrays.copyOfRange(Byte_input, 2,4)).intValue();

        if (Byte_input == null){
            return 0;
        }
        for(int i = 0; i<12; i++){
            b[i]= Byte_input[i];
        }

        int next_index = 12;
        for (int i =12;i<Byte_input.length; i++){
            if (i%2 ==0){
                int zero_counter = Byte_input[i];
                for (int j = 0 ; j<zero_counter; j++){
                    b[next_index]= 0;
                    next_index ++;
                }
            }
            else{
                int one_counter = Byte_input[i];
                for (int j = 0 ; j<one_counter; j++){
                    b[next_index]= 1;
                    next_index ++;
                }
            }
        }



        return 0;
    }

    @Override
    public int read() throws IOException {

        return 0;
    }
}

