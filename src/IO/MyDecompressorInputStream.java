package IO;

import algorithms.mazeGenerators.Position;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;

public class MyDecompressorInputStream extends InputStream{
    private InputStream in;
    public MyDecompressorInputStream(InputStream b) {in = b;}

    @Override
    public int read(byte[] b) throws IOException {return 0;}

    @Override
    public int read() throws IOException {
        return 0;
    }
}
