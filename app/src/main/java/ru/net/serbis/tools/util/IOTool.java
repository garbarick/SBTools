package ru.net.serbis.tools.util;

import java.io.*;

public class IOTool
{
    public static void close(Closeable o)
    {
        try
        {
            if (o == null)
            {
                return;
            }
            o.close();
        }
        catch (Exception e)
        {}
    }

    public static void copy(InputStream is, OutputStream os, boolean closeIn, boolean closeOut) throws Exception
    {
        try
        {
            byte[] buf = new byte[10240];
            int len;
            while ((len = is.read(buf)) > 0)
            {
                os.write(buf, 0, len);
            }
        }
        finally
        {
            if (closeIn)
            {
                close(is);
            }
            if (closeOut)
            {
                close(os);
            }
        }
    }
}
