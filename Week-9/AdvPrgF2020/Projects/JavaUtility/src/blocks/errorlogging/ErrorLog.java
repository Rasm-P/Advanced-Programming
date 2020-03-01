package blocks.errorlogging;

import java.io.IOException;

public interface ErrorLog
{
    public void reportIOException(IOException e);
}
