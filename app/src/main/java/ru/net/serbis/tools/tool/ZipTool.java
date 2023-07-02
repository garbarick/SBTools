package ru.net.serbis.tools.tool;

import android.content.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;
import ru.net.serbis.tools.*;
import java.nio.file.attribute.*;

public class ZipTool
{
    private Context context;
    private Progress progress;
    private File dir;
    private File result;
    private int current;
    private int all;
    private File temp;

    public ZipTool(Context context, Progress progress, File dir, File result)
    {
        this.context = context;
        this.progress = progress;
        this.dir = dir;
        this.result = result;
    }

    public void make() throws Exception
    {
        List<File> files = getFiles(dir);
        if (files.isEmpty())
        {
            throw new Exception(context.getResources().getString(R.string.error_no_files));
        }
        if (result.exists())
        {
            temp = File.createTempFile(result.getName(), ".zip", dir);
            updateFiles(files);
            result.delete();
            temp.renameTo(result);
        }
        else
        {
            addFiles(files);
        }
        clearFiles(files);
    }

    private List<File> getFiles(File dir)
    {
        File[] files = dir.listFiles();
        if (files == null)
        {
            return Collections.emptyList();
        }
        List<File> result = new ArrayList<File>(Arrays.asList(files));
        result.remove(this.result);
        if (temp != null)
        {
            result.remove(temp);
        }
        return result;
    }

    private void addFiles(List<File> files) throws Exception
    {
        ZipOutputStream out = null;
        try
        {
            out = new ZipOutputStream(new FileOutputStream(result));
            addFiles(files, out);
        }
        finally
        {
            IOTool.close(out);
        }
    }

    private void updateFiles(List<File> files) throws Exception
    {
        ZipOutputStream out = null;
        try
        {
            all = getEntriesCount();
            out = new ZipOutputStream(new FileOutputStream(temp));
            addFiles(out);
            addFiles(files, out);
        }
        finally
        {
            IOTool.close(out);
        }
    }
    
    private int getEntriesCount() throws Exception
    {
        int count = 0;
        ZipFile zip = null;
        try
        {
            zip = new ZipFile(result);
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while(entries.hasMoreElements())
            {
                count ++;
                entries.nextElement();
            }
            return count;
        }
        finally
        {
            IOTool.close(zip);
        }
    }
    
    private void addFiles(ZipOutputStream out) throws Exception
    {
        ZipInputStream in = null;
        try
        {
            in = new ZipInputStream(new FileInputStream(result));
            ZipEntry inEntry = null;
            while((inEntry = in.getNextEntry()) != null)
            {
                ZipEntry outEntry = new ZipEntry(inEntry);
                out.putNextEntry(outEntry);
                IOTool.copy(in, out, false, false);
                current ++;
                progress.progress(UITool.getPercent(all, current));
            }
        }
        finally
        {
            IOTool.close(in);
        }
    }

    private void addFiles(List<File> files, ZipOutputStream out) throws Exception
    {
        if (files == null)
        {
            return;
        }
        all += files.size();
        for(File file : files)
        {
            if (file.isDirectory())
            {
                addFiles(getFiles(file), out);
            }
            else
            {
                try
                {
                    out.putNextEntry(createEntry(file));
                    FileInputStream in = new FileInputStream(file);
                    IOTool.copy(in, out, true, false);
                    out.closeEntry();
                }
                catch (Exception e)
                {
                }
            }
            current ++;
            progress.progress(UITool.getPercent(all, current));
        }
    }

    private ZipEntry createEntry(File file)
    {
        String path = dir.toPath().relativize(file.toPath()).toString();
        ZipEntry entry = new ZipEntry(path);
        entry.setLastModifiedTime(FileTime.fromMillis(file.lastModified()));
        return entry;
    }

    private void clearFiles(List<File> files)
    {
        if (files == null)
        {
            return;
        }
        all += files.size();
        for(File file : files)
        {
            if (file.isDirectory())
            {
                clearFiles(getFiles(file));
            }
            file.delete();
            current ++;
            progress.progress(UITool.getPercent(all, current));
        }
    }
}
