package ru.net.serbis.tools.task;

import android.os.*;
import java.util.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class SearchPackage extends AsyncTask<String, Integer, String> implements Progress
{
    private TaskCallback<String> callback;
    private TaskError error;
    
    public SearchPackage(TaskCallback<String> callback)
    {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String[] params)
    {
        try
        {
            publishProgress(0);
            String path = params[0];
            return run(path);
        }
        catch (Exception e)
        {
            Log.error(this, e);
            error = new TaskError(Constants.ERROR_SEARCH_PACKAGE, e.getMessage());
            return null;
        }
        finally
        {
            publishProgress(0);
        }
    }

    @Override
    protected void onProgressUpdate(Integer[] progress)
    {
        callback.progress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result)
    {
        callback.onResult(result, error);
    }

    private String run(String path)
    {
        Set<String> result = new Sources().getPackages(path, this);
        if (result.isEmpty())
        {
            return "";
        }
        return result.iterator().next();
    }

    @Override
    public void progress(int percent)
    {
        publishProgress(percent);
    }
}
