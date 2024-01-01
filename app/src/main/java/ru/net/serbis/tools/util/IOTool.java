package ru.net.serbis.tools.util;

import android.os.*;
import java.io.*;
import java.util.*;

public class IOTool
{
    private static final IOTool instance = new IOTool();

    public static IOTool get()
    {
        return instance;
    }

    public void close(Object o)
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

    public void copy(InputStream is, OutputStream os, boolean closeIn, boolean closeOut, int bufferSize) throws Exception
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
    
    public void copyQuietly(InputStream is, OutputStream os, boolean closeIn, boolean closeOut, int bufferSize)
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

    public File getDownloadFile()
    {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

    public String getDownloadPath()
    {
        return getDownloadFile().getAbsolutePath();
    }

    public File getDownloadFile(String path)
    {
        return new File(getDownloadFile(), path);
    }
    
    public String getDownloadPath(String path)
    {
        return getDownloadFile(path).getAbsolutePath();
    }
    
    public Set<String> findFiles(String fileListPath, List<String> extensions)
    {
        Set<String> result = new TreeSet<String>();
        File file = new File(fileListPath);
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (checkExt(line, extensions))
                {
                    result.add(line);
                }
            }
        }
        catch (Throwable e)
        {
            Log.error(this, e);
        }
        finally
        {
            close(reader);
            file.delete();
        }
        return result;
	}
    
    private String getExt(String fileName)
    {
        String ext = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0)
        {
            ext = fileName.substring(i + 1).toLowerCase();
        }
        return ext;
    }
    
    public boolean checkExt(String fileName, List<String> extensions)
    {
        return extensions.contains(getExt(fileName));
	}
    
    public boolean checkExt(File file, List<String> extensions)
    {
        return checkExt(file.getName(), extensions);
	}
}
