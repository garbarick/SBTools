package ru.net.serbis.tools.data;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ZipParams
{
    public File dir;
    public File result;
    public int compression;
    public boolean deleteSourceFiles;
    public int bufferSize;
    public List<Pattern> excludes = new ArrayList<Pattern>();

    public void addExclude(String exclude)
    {
        excludes.add(Pattern.compile(exclude));
    }
}
