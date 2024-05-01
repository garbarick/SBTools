package ru.net.serbis.tools.util;

import android.text.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.result.*;

public class Sources
{
    private static final Pattern PACKAGE = Pattern.compile("package\\s+?(\\S+?);", Pattern.MULTILINE);

    public Set<String> getPackages(String path, Progress progress)
    {
        File source = new File(path);
        PackagesResult result = new PackagesResult(progress);
        findPackages(source, result);
        return result.get();
    }

    private void findPackages(File source, PackagesResult result)
    {
        if (source.isDirectory())
        {
            findPackagesInDir(source, result);
            return;
        }
        String packageName = getPackage(source);
        if (!TextUtils.isEmpty(packageName))
        {
            result.add(packageName);
        }
        result.progress();
    }

    private void findPackagesInDir(File source, PackagesResult result)
    {
        File[] files = getFiles(source);
        if (files == null)
        {
            return;
        }
        result.count(files.length);
        for (File file : files)
        {
            findPackages(file, result);
        }
    }

    private File[] getFiles(File source)
    {
        return source.listFiles(
            new FileFilter()
            {
                @Override
                public boolean accept(File file)
                {
                    return file.isDirectory() || file.getName().endsWith(Constants.JAVA_EXT);
                }
            }
        );
    }

    private String getPackage(File file)
    {
        String content = IOTool.get().readFile(file);
        Matcher matcher = PACKAGE.matcher(content);
        if (matcher.find())
        {
            return matcher.group(1);
        }
        return null;
    }

    public void copy(String source, String sourcePackage, String target, String targetPackage, Progress progress)
    {
        CopyResult result = new CopyResult(progress, sourcePackage, targetPackage);
        copy(new File(source), new File(target), result);
        String count = String.format(Strings.get().get(R.string.files_count), result.getCountFiles());
        UITool.get().toast(count);
    }

    private void copy(File source, File target, CopyResult result)
    {
        if (source.isDirectory())
        {
            copyDir(source, target, result);
            return;
        }
        File targetFile = result.getTargetFile(source, target);
        if (targetFile != null)
        {
            targetFile.getParentFile().mkdirs();
            String content = IOTool.get().readFile(source);
            content = result.changePackage(content);
            IOTool.get().copy(content, targetFile);
            result.addFile();
        }
        result.progress();
    }

    private void copyDir(File source, File target, CopyResult result)
    {
        File[] files = getFiles(source);
        if (files == null)
        {
            return;
        }
        result.count(files.length);
        for (File file : files)
        {
            copy(file, target, result);
        }
    }
}
