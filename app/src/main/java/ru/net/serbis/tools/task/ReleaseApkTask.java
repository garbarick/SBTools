package ru.net.serbis.tools.task;

import android.content.*;
import android.content.pm.*;
import android.os.*;
import java.io.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ReleaseApkTask extends AsyncTask<Void, Integer, Boolean>
{
    private Context context;
    private TaskCallback<Boolean> callback;
    private TaskError error;

    public ReleaseApkTask(Context context, TaskCallback<Boolean> callback)
    {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Void... params)
    {
        try
        {
            UITool.get().setProgress(true);
            publishProgress(0);
            copyApk();
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
        String name = appDir.getName();
        File dir = new File(Params.ZIP_RESULT_DIR.getValue());
        File releaseDir = new File(appDir, "release");

        PackageManager packageManager = context.getPackageManager();
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(PackageManager.SIGNATURE_MATCH))
        {
            ApplicationInfo appInfo = packageInfo.applicationInfo;
            String appName = appInfo.loadLabel(packageManager).toString();
            if (name.equals(appName))
            {
                File apk = new File(appInfo.publicSourceDir);
                String apkName = name + "  " + packageInfo.versionName + ".apk";
                IOTool.get().copy(apk, new File(dir, apkName));
                IOTool.get().copy(apk, new File(releaseDir, apkName));
            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer[] progress)
    {
        callback.progress(progress[0]);
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        UITool.get().setProgress(false);
        callback.onResult(result, error);
    }
}
