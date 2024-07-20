package ru.net.serbis.tools.task;

import android.content.*;
import android.content.pm.*;
import android.os.*;
import java.io.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ReleaseApkTask extends AsyncTask<PackageInfo, Integer, Boolean>
{
    private Context context;
    private TaskCallback<Boolean> callback;
    private TaskError error;
    private PackageInfo packInfo;

    public ReleaseApkTask(Context context, TaskCallback<Boolean> callback)
    {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(PackageInfo... params)
    {
        try
        {
            UITool.get().setProgress(true);
            publishProgress(0);
            if (params.length > 0)
            {
                packInfo = params[0];
                if (Params.RELEASE_APK.getValue())
                {
                    copyApk();
                }
            }
            return true;
        }
        catch(Throwable e)
        {
            Log.error(this, e);
            error = new TaskError(Constants.ERROR_ZIP_DIR, e.getMessage());
            return false;
        }
        finally
        {
            publishProgress(0);
        }
    }

    private void copyApk()
    {
        File appDir = new File(Params.SOURCE_APP_DIR.getValue());
        String appName = appDir.getName();
        File dir = new File(Params.ZIP_RESULT_DIR.getValue());
        File releaseDir = new File(appDir, "release");
        
        ApplicationInfo appInfo = packInfo.applicationInfo;
        File apk = new File(appInfo.publicSourceDir);
        String apkName = appName + "  " + packInfo.versionName + ".apk";
        IOTool.get().copy(apk, new File(dir, apkName));
        IOTool.get().copy(apk, new File(releaseDir, apkName));
    }

    @Override
    protected void onProgressUpdate(Integer[] progress)
    {
        callback.progress(progress[0]);
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        if (packInfo != null && Params.RELEASE_JAR.getValue())
        {
            startReleaseJar();
        }
        else
        {
            UITool.get().setProgress(false);
            callback.onResult(result, error);
        }
    }

    private void startReleaseJar()
    {
        File appDir = new File(Params.SOURCE_APP_DIR.getValue());
        String appName = appDir.getName();
        String jarName = appName + "  " + packInfo.versionName + ".jar";

        ZipParams params = new ZipParams();
        params.dir = new File(appDir, "app/build/bin/classesrelease");
        params.result = new File(appDir, "release/" + jarName);
        params.result.delete();
        params.compression = 6;
        params.deleteSourceFiles = false;
        params.bufferSize = Constants.BUFFER_SIZE;
        params.addExclude("adrt/.*");
        params.addExclude(".*?\\.dex");
        params.notifyResult = false;

        new ZipTask(context, callback).execute(params);
    }
}
