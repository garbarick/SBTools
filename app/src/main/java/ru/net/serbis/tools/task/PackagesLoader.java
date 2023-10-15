package ru.net.serbis.tools.task;

import android.content.*;
import android.content.pm.*;
import android.graphics.drawable.*;
import android.os.*;
import java.util.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class PackagesLoader extends AsyncTask<PackageItem, Integer, Collection<ActivityItem>>
{
    private Context context;
    private TaskCallback<Collection<ActivityItem>> callback;
    private TaskError error;

    public PackagesLoader(Context context, TaskCallback<Collection<ActivityItem>> callback)
    {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected Collection<ActivityItem> doInBackground(PackageItem ... params)
    {
        publishProgress(0);
        try
        {
            if (params.length > 0)
            {
                PackageItem item = params[0];
                return getActivityItems(item);
            }
            return getPackageItems();
        }
        catch (Exception e)
        {
            Log.error(this, e);
            error = new TaskError(Constants.ERROR_ACTIVITIES, e.getMessage());
            return null;
        }
        finally
        {
            publishProgress(0);
        }
    }

    private Collection<ActivityItem> getActivityItems(PackageItem item) throws Exception
    {
        item.getChildren().clear();
        PackageManager manager = context.getPackageManager();
        PackageInfo packageInfo = manager.getPackageInfo(item.getPackageName(), PackageManager.GET_ACTIVITIES);
        
        ActivityInfo[] aInfos = packageInfo.activities;
        int count = aInfos.length;
        int i = 0;
        for (ActivityInfo aInfo : aInfos)
        {
            item.add(getActivityItem(manager, aInfo, item.getPackageName()));
            i++;
            publishProgress(UITool.get().getPercent(count, i));
        }
        return item.getChildren();
    }
    
    private ActivityItem getActivityItem(PackageManager manager, ActivityInfo aInfo, String packageName)
    {
        return new ActivityItem(
            aInfo.loadLabel(manager).toString().trim(),
            getDrawable(aInfo, manager),
            aInfo.name,
            packageName
        );
    }

    private Drawable getDrawable(ActivityInfo aInfo, PackageManager manager)
    {
        try
        {
            return aInfo.loadIcon(manager);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private Collection<ActivityItem> getPackageItems() throws Exception
    {
        Collection<ActivityItem> result = new TreeSet<ActivityItem>();
        PackageManager manager = context.getPackageManager();
        List<PackageInfo> packages = manager.getInstalledPackages(PackageManager.GET_ACTIVITIES);

        int count = packages.size();
        int i = 0;
        for (PackageInfo pInfo : packages)
        {
            ActivityInfo[] aInfos = pInfo.activities;
            if (aInfos != null && aInfos.length > 0)
            {
                PackageItem pItem = getPackageItem(manager, pInfo);
                result.add(pItem);
            }
            i++;
            publishProgress(UITool.get().getPercent(count, i));
        }
        return result;
    }

    private PackageItem getPackageItem(PackageManager manager, PackageInfo pInfo)
    {
        String packageName = pInfo.packageName;
        String label = manager.getApplicationLabel(pInfo.applicationInfo).toString();
        Drawable drawable = manager.getApplicationIcon(pInfo.applicationInfo);
        return new PackageItem(label, drawable, packageName, pInfo.activities.length);
    }

    @Override
    protected void onProgressUpdate(Integer... progress)
    {
        callback.progress(progress[0]);
    }

    @Override
    protected void onPostExecute(Collection<ActivityItem> result)
    {
        if (error == null)
        {
            callback.onResult(result, null);
            return;
        }
        callback.onResult(null, error);
    }
}
