package ru.net.serbis.tools.extension.share;

import android.content.*;
import android.os.*;
import android.text.*;
import java.io.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.connection.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.bean.*;

public class ShareTools
{
    protected Context context;
    protected App app;

    public ShareTools(Context context)
    {
        this.context = context;
        app = (App) context.getApplicationContext();
    }

    private void sendServiceAction(int action, Map<String, String> request, Handler reply)
    {
        ExtConnection connection = app.getShareConnection();
        if (!connection.isBound())
        {
            return;
        }
        Message msg = Message.obtain(null, action, 0, 0);
        Bundle data = new Bundle();
        for (Map.Entry<String, String> entry : request.entrySet())
        {
            data.putString(entry.getKey(), entry.getValue());
        }
        msg.setData(data);
        msg.replyTo = new Messenger(reply);
        try
        {
            connection.getService().send(msg);
        }
        catch (RemoteException e)
        {
            Log.error(this, e);
        }
    }

    public void uploadFile(final TaskCallback<Boolean> callback, String filePath, String shareDir, Integer bufferSize)
    {
        TaskError error = validateUploadFile(filePath, shareDir);
        if (error != null)
        {
            onResult(callback, false, error);
            return;
        }

        Map<String, String> request = new HashMap<String, String>();
        request.put(Share.FILE, filePath);
        request.put(Share.PATH, shareDir);
        request.put(Share.BUFFER_SIZE, bufferSize.toString());

        sendServiceAction(
            Share.ACTION_UPLOAD,
            request,
            new Handler(Looper.getMainLooper())
            {
                @Override
                public void handleMessage(Message msg)
                {
                    if (msg.getData().containsKey(Share.RESULT) &&
                        Share.SUCCESS.equals(msg.getData().getString(Share.RESULT)))
                    {
                        onResult(callback, true, null);
                    }
                    else if (msg.getData().containsKey(Share.ERROR) &&
                             msg.getData().containsKey(Share.ERROR_CODE))
                    {
                        int errorCode = msg.getData().getInt(Share.ERROR_CODE);
                        String error = msg.getData().getString(Share.ERROR);
                        onResult(callback, false, new TaskError(errorCode, error));
                    }
                    else if (msg.getData().containsKey(Share.PROGRESS))
                    {
                        int progress = msg.getData().getInt(Share.PROGRESS);
                        progress(callback, progress);
                    }
                }
            }
        );
	}

    private TaskError validateUploadFile(String filePath, String shareDir)
    {
        TaskError error = error = new TaskError(Constants.ERROR_FILE_IS_NOT_FOUND, R.string.error_file_is_not_found);
        if (TextUtils.isEmpty(filePath) ||
            TextUtils.isEmpty(shareDir))
        {
            return error;
        }
        File file = new File(filePath);
        if (!file.exists() || !file.isFile())
        {
            return error;
        }
        return null;
    }

    private int lastProgress = -1;

    public <T> void  progress(final TaskCallback<T> callback, int progress)
    {
        try
        {
            if (lastProgress != progress)
            {
                callback.progress(progress);
            }
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
        finally
        {
            lastProgress = progress;
        }
    }

    public <T> void onResult(final TaskCallback<T> callback, T result, TaskError error)
    {
        try
        {
            callback.onResult(result, error);
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
        finally
        {
            lastProgress = -1;
        }
    }
}
