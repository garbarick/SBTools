package ru.net.serbis.tools.task;

import android.content.*;
import android.os.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.bean.*;

import ru.net.serbis.tools.R;

public class ZipTask extends AsyncTask<ZipParams, Integer, Boolean> implements Progress
{
    private Context context;
    private TaskCallback<Boolean> callback;
    private TaskError error;

    public ZipTask(Context context, TaskCallback<Boolean> callback)
    {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(ZipParams ... data)
    {
        try
        {
            UITool.get().setProgress(true);
            publishProgress(0);
            ZipParams params = data[0];
            if (params.dir.isDirectory() && params.dir.exists())
            {
                new ZipTool(context, this, params).make();
                return true;
            }
            else
            {
                error = new TaskError(Constants.ERROR_FILE_IS_NOT_FOUND, R.string.error_file_is_not_found);
                return false;
            }
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

    @Override
    protected void onProgressUpdate(Integer[] progress)
    {
        callback.progress(progress[0]);
    }
    
    @Override
    public void progress(int progress)
    {
        publishProgress(progress);
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        UITool.get().setProgress(false);
        callback.onResult(result, error);
    }
}
