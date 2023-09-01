package ru.net.serbis.tools.util;

import android.os.*;
import java.io.*;

public class IOTool
{
    public static void close(Object o)
    {
        try
        {
            if (o == null)
            {
                return;
            }
            if (o instanceof Closeable)
            {
                ((Closeable)o).close();
            }
            else if (o instanceof AutoCloseable)
            {
                ((AutoCloseable)o).close();
            }
        }
        catch (Exception e)
        {}
    }

    public static void copy(InputStream is, OutputStream os, boolean closeIn, boolean closeOut, int bufferSize) throws Exception
    {
        try
        {
            byte[] buf = new byte[bufferSize];
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
    
    public static void copyQuietly(InputStream is, OutputStream os, boolean closeIn, boolean closeOut, int bufferSize)
    {
        try
        {
            copy(is, os, closeIn, closeOut, bufferSize);
        }
        catch (Exception e)
        {
            Log.error(new IOTool(), e);
        }
    }

    public static File getDownloadFile()
    {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

    public static String getDownloadPath()
    {
        return getDownloadFile().getAbsolutePath();
    }

    public static File getDownloadFile(String path)
    {
        return new File(getDownloadFile(), path);
    }
    
    public static String getDownloadPath(String path)
    {
        return getDownloadFile(path).getAbsolutePath();
    }
}
