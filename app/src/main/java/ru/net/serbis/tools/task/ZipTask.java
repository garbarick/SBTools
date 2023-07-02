package ru.net.serbis.tools.task;

import android.content.*;
import android.os.*;
import java.io.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.tool.*;

public class ZipTask extends AsyncTask<File, Integer, Boolean> implements Progress
{
    private Context context;
    private TaskCallback callback;
    private TaskError error;

    public ZipTask(Context context, TaskCallback callback)
    {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(File... params)
    {
        try
        {
            publishProgress(0);
            File dir = params[0];
            File file = params[1];

            if (dir.isDirectory() && dir.exists())
            {
                new ZipTool(context, this, dir, file).make();
                return true;
            }
            else
            {
                error = new TaskError(context, Constants.ERROR_FILE_IS_NOT_FOUND, R.string.error_file_is_not_found);
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
        callback.onResult(result, error);
    }
}
