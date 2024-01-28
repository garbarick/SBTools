package ru.net.serbis.tools.task;

import android.os.*;
import java.io.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ClearTrashTask extends AsyncTask<String, Integer, Integer>
{
    private TaskError error;
    private TaskCallback<Integer> callback;
    private int filesDeleted;

    public ClearTrashTask(TaskCallback<Integer> callback)
    {
        this.callback = callback;
    }

    @Override
    protected Integer doInBackground(String... pathes)
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
        }
        catch (Exception e)
        {
            error = new TaskError(Constants.ERROR_CLEAR_TRASH, e.getMessage());
        }
        finally
        {
            publishProgress(0);
        }
        return filesDeleted;
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
            if (file.delete())
            {
                filesDeleted ++;
            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer... progress)
    {
        callback.progress(progress[0]);
    }

    @Override
    protected void onPostExecute(Integer result)
    {
        if (error == null)
        {
            callback.onResult(result, null);
            return;
        }
        callback.onResult(null, error);
    }
}
