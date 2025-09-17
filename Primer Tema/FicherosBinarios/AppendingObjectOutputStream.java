package FicherosBinarios;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class AppendingObjectOutputStream extends ObjectOutputStream {
    public AppendingObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset();// do not write a header
    }
}
