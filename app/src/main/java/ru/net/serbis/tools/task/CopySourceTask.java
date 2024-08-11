package ru.net.serbis.tools.task;

import android.os.*;
import android.text.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.bean.*;

public class CopySourceTask extends AsyncTask<Void, Integer, Boolean> implements Progress
{
    private TaskCallback<Boolean> callback;
    private TaskError error;

    public CopySourceTask(TaskCallback<Boolean> callback)
    {
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Void[] p1)
    {
        try
        {
            validate(Params.SOURCE);
            validate(Params.SOURCE_PACKAGE);
            validate(Params.TARGET);
            validate(Params.TARGET_PACKAGE);
            new Sources().copy(
                Params.SOURCE.getValue(),
                Params.SOURCE_PACKAGE.getValue(),
                Params.TARGET.getValue(),
                Params.TARGET_PACKAGE.getValue(),
                this);
            publishProgress(0);
            return true;
        }
        catch (Exception e)
        {
            Log.error(this, e);
            error = new TaskError(Constants.ERROR_COPY, e.getMessage());
            return false;
        }
        finally
        {
            publishProgress(0);
        }
    }

    private void validate(Param<String, ?> param)
    {
        if (TextUtils.isEmpty(param.getValue()))
        {
            String error = Strings.get().get(R.string.can_not_by_empty, param.getName());
            throw new RuntimeException(error);
        }
    }

    @Override
    protected void onProgressUpdate(Integer[] values)
    {
        callback.progress(values[0]);
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        callback.onResult(result, error);
    }

    @Override
    public void progress(int percent)
    {
        callback.progress(percent);
    }
}
