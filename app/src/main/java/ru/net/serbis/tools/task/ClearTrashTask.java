package ru.net.serbis.tools.task;

import android.content.*;
import android.os.*;
import java.io.*;
import java.util.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.bean.*;

public class ClearTrashTask extends AsyncTask<Void, Integer, Integer>
{
    private TaskError error;
    private TaskCallback<Integer> callback;
    private int filesDeleted;
    private Set<String> trashFiles;
    private Set<String> dirToFiles;

    public ClearTrashTask(Context context, TaskCallback<Integer> callback)
    {
        this.callback = callback;
        trashFiles = Params.TRASH_FILES.getValue();
        dirToFiles = Params.DIRS_TO_FILES.getValue();
    }

    @Override
    protected Integer doInBackground(Void... pathes)
    {
        publishProgress(0);
        try
        {
            int count = trashFiles.size() + dirToFiles.size();
            int i = 0;
            for (String trashFile : trashFiles)
            {
                File file = new File(trashFile);
                if (!dirToFiles.contains(trashFile))
                {
                    deleteFile(file);
                }
                publishProgress(UITool.get().getPercent(count, ++i));
            }
            for (String dirToFile : dirToFiles)
            {
                File file = new File(dirToFile);
                if (file.isDirectory())
                {
                    deleteFile(file);
                }
                if (!file.exists())
                {
                    file.createNewFile();
                }
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
        callback.onResult(result, error);
    }
}
