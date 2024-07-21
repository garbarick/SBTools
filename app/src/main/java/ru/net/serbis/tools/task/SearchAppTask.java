package ru.net.serbis.tools.task;

import android.content.*;
import android.content.pm.*;
import android.os.*;
import ru.net.serbis.utils.*;

public class SearchAppTask extends AsyncTask<String, Integer, PackageInfo>
{
    private Context context;
    private TaskCallback<Boolean> callback;
    private AsyncTask<PackageInfo, Integer, Boolean> nextTask;

    public SearchAppTask(
        Context context,
        TaskCallback<Boolean> callback,
        AsyncTask<PackageInfo, Integer, Boolean> nextTask)
    {
        this.context = context;
        this.callback = callback;
        this.nextTask = nextTask;
    }

    @Override
    protected PackageInfo doInBackground(String... params)
    {
        try
        {
            publishProgress(0);
            UITool.get().setProgress(true);
            String appName = params[0];
            return searchApp(appName);
        }
        finally
        {
            publishProgress(0);
        }
    }

    private PackageInfo searchApp(String name)
    {
        PackageManager packageManager = context.getPackageManager();
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(PackageManager.SIGNATURE_MATCH))
        {
            ApplicationInfo appInfo = packageInfo.applicationInfo;
            String appName = appInfo.loadLabel(packageManager).toString();
            if (name.equals(appName))
            {
                return packageInfo;
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer[] progress)
    {
        callback.progress(progress[0]);
    }

    @Override
    protected void onPostExecute(PackageInfo result)
    {
        UITool.get().setProgress(false);
        nextTask.execute(result);
    }
}
