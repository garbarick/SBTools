package ru.net.serbis.tools.task;

import android.content.*;
import android.os.*;
import java.io.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.tools.adapter.*;

public class ZipTask extends AsyncTask<Void, Integer, Boolean> implements Progress
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
    protected Boolean doInBackground(Void... params)
    {
        try
        {
            UITool.get().setProgress(context, true);
            publishProgress(0);
            File dir = new File(Params.DIRECTORY.getValue(context));
            File file = new File(dir, Params.ZIP_NAME.getValue(context));
            int compression = Params.COMPRESSION.getValue(context).getLevel();
            boolean deleteSourceFiles = Params.DELETE_SOURCE_FOLRS.getValue(context);

            if (dir.isDirectory() && dir.exists())
            {
                new ZipTool(context, this, dir, file, compression, deleteSourceFiles).make();
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
        UITool.get().setProgress(context, false);
        callback.onResult(result, error);
    }
}
