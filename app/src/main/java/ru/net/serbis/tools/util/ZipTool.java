package ru.net.serbis.tools.util;

import android.content.*;
import java.io.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.util.zip.*;
import ru.net.serbis.tools.*;

public class ZipTool
{
    private Context context;
    private Progress progress;
    private File dir;
    private File result;
    private int current;
    private int all;
    private File temp;
    private int compression;
    private boolean deleteSourceFiles;
    private int bufferZise;
    private int entryCount;

    public ZipTool(Context context, 
                   Progress progress,
                   File dir,
                   File result,
                   int compression,
                   boolean deleteSourceFiles,
                   int bufferSize)
    {
        this.context = context;
        this.progress = progress;
        this.dir = dir;
        this.result = result;
        this.compression = compression;
        this.deleteSourceFiles = deleteSourceFiles;
        this.bufferZise = bufferSize;
    }

    public void make() throws Exception
    {
        List<File> files = getFiles(dir);
        if (files.isEmpty())
        {
            throw new Exception(Strings.get().get(R.string.error_no_files));
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
        if (deleteSourceFiles)
        {
            clearFiles(files);
        }
        showResult();
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
            out.setLevel(compression);
            addFiles(files, out);
        }
        finally
        {
            IOTool.get().close(out);
        }
    }

    private void updateFiles(List<File> files) throws Exception
    {
        ZipOutputStream out = null;
        try
        {
            all = getEntriesCount();
            out = new ZipOutputStream(new FileOutputStream(temp));
            out.setLevel(compression);
            addFiles(out);
            addFiles(files, out);
        }
        finally
        {
            IOTool.get().close(out);
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
            while (entries.hasMoreElements())
            {
                count ++;
                entries.nextElement();
            }
            return count;
        }
        finally
        {
            IOTool.get().close(zip);
        }
    }

    private void addFiles(ZipOutputStream out) throws Exception
    {
        ZipInputStream in = null;
        try
        {
            in = new ZipInputStream(new FileInputStream(result));
            ZipEntry inEntry = null;
            while ((inEntry = in.getNextEntry()) != null)
            {
                ZipEntry outEntry = new ZipEntry(inEntry);
                out.putNextEntry(outEntry);
                entryCount ++;
                IOTool.get().copy(in, out, false, false, bufferZise);
                current ++;
                progress.progress(UITool.get().getPercent(all, current));
            }
        }
        finally
        {
            IOTool.get().close(in);
        }
    }

    private void addFiles(List<File> files, ZipOutputStream out) throws Exception
    {
        if (files == null)
        {
            return;
        }
        all += files.size();
        for (File file : files)
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
                    entryCount ++;
                    FileInputStream in = new FileInputStream(file);
                    IOTool.get().copy(in, out, true, false, bufferZise);
                    out.closeEntry();
                }
                catch (Exception e)
                {
                }
            }
            current ++;
            progress.progress(UITool.get().getPercent(all, current));
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
        for (File file : files)
        {
            if (file.isDirectory())
            {
                clearFiles(getFiles(file));
            }
            file.delete();
            current ++;
            progress.progress(UITool.get().getPercent(all, current));
        }
    }

    private void showResult()
    {
        String text = String.format(Strings.get().get(R.string.zip_size), getFileSize(result));
        text += ", ";
        text += String.format(Strings.get().get(R.string.files_count), entryCount);
        UITool.get().toast(text);
    }

    private String getFileSize(File file)
    {
        long size = file.length();

        float sizeKb = 1024.0f;
        float sizeMb = sizeKb * sizeKb;
        float sizeGb = sizeMb * sizeKb;
        float sizeTerra = sizeGb * sizeKb;

        if (size < sizeMb)
        {
            return String.format("%,.2f Kb", size / sizeKb);
        }
        else if (size < sizeGb)
        {
            return String.format("%,.2f Mb", size / sizeMb);
        }
        else if (size < sizeTerra)
        {
            return String.format("%,.2f Gb", size / sizeGb);
        }
        return "";
    }
}
