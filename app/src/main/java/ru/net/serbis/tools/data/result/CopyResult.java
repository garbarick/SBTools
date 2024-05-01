package ru.net.serbis.tools.data.result;

import java.io.*;
import ru.net.serbis.tools.util.*;

public class CopyResult extends Result
{
    private String sourcePackage;
    private String targetPackage;
    private int countFiles;

    public CopyResult(Progress progress, String sourcePackage, String targetPackage)
    {
        super(progress);
        this.sourcePackage = sourcePackage;
        this.targetPackage = targetPackage;
    }

    public File getTargetFile(File source, File target)
    {
        String name = source.getName();
        String path = source.getParentFile().getAbsolutePath().replace("/", ".");
        int pos = path.indexOf(sourcePackage);
        if (pos > 0)
        {
            path = path.substring(pos);
            path = path.replace(sourcePackage, targetPackage).replace(".", "/");
            return new File(new File(target, path), name);
        }
        return null;
    }

    public String changePackage(String content)
    {
        return content.replace(sourcePackage, targetPackage);
    }

    public void addFile()
    {
        countFiles ++;
    }

    public int getCountFiles()
    {
        return countFiles;
    }
}
