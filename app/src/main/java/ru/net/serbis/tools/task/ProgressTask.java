package ru.net.serbis.tools.task;

import android.content.*;
import android.os.*;
import ru.net.serbis.tools.util.*;

public class ProgressTask extends AsyncTask<Integer, Integer, Boolean>
{
    private Context context;
    private TaskCallback callback;

    public ProgressTask(Context context, TaskCallback callback)
    {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Integer... params)
    {
        int sleep = params[0];
        int count = params[1];
        try
        {
            for (int i = 0; i <= count; i ++)
            {
                publishProgress(UITool.getPercent(count, i));
                Thread.currentThread().sleep(sleep);
            }
        }
        catch (Exception e)
        {
        }
        finally
        {
            publishProgress(0);
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... progress)
    {
        callback.progress(progress[0]);
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        callback.onResult(result, null);
    }
}
