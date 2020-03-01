package blocks.errorlogging.impl;

import blocks.errorlogging.ErrorLog;

import java.io.IOException;

public class StdError implements ErrorLog
{
    @Override
    public void reportIOException(IOException e)
    {
        e.printStackTrace();
    }
}
