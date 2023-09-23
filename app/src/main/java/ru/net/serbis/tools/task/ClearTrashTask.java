package ru.net.serbis.tools.task;

import android.os.*;
import java.io.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ClearTrashTask extends AsyncTask<String, Integer, Boolean>
{
    private TaskError error;
    private TaskCallback<Boolean> callback;

    public ClearTrashTask(TaskCallback<Boolean> callback)
    {
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(String... pathes)
    {
        publishProgress(0);
        try
        {
            int count = pathes.length;
            int i = 0;
            for (String path : pathes)
            {
                File file = new File(path);
                deleteFile(file);
                publishProgress(UITool.get().getPercent(count, ++i));
            }
            return true;
        }
        catch (Exception e)
        {
            error = new TaskError(Constants.ERROR_CLEAR_TRASH, e.getMessage());
        }
        finally
        {
            publishProgress(0);
        }
        return false;
    }
    
    private void deleteFile(File file)
    {
        if (file.exists())
        {
            if (file.isDirectory())
            {
                file.listFiles(
                    new FileFilter()
                    {
                        public boolean accept(File file)
                        {
                            deleteFile(file);
                            return false;
                        }
                    }
                );
            }
            file.delete();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress)
    {
        callback.progress(progress[0]);
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        if (error == null)
        {
            callback.onResult(result, null);
            return;
        }
        callback.onResult(null, error);
    }
}
