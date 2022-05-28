package IO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream{
    private InputStream in;
    public MyDecompressorInputStream(InputStream b) {in = b;}

    @Override
    public int read() throws IOException {
        return 0;
    }
}
