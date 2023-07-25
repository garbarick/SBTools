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
import ru.net.serbis.tools.util.*;

public class ShareTools implements TaskCallback
{
    protected Context context;
    protected App app;
    TaskCallback callback;

    public ShareTools(Context context, TaskCallback callback)
    {
        this.context = context;
        app = (App) context.getApplicationContext();
        this.callback = callback;
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

    public void uploadFile(String filePath, String shareDir)
    {
        UITool.get().setProgress(context, true);

        TaskError error = validate(filePath, shareDir);
        if (error != null)
        {
            onResult(false, error);
            return;
        }

        Map<String, String> request = new HashMap<String, String>();
        request.put(Share.FILE, filePath);
        request.put(Share.PATH, shareDir);

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
                        onResult(true, null);
                    }
                    else if (msg.getData().containsKey(Share.ERROR) &&
                             msg.getData().containsKey(Share.ERROR_CODE))
                    {
                        int errorCode = msg.getData().getInt(Share.ERROR_CODE);
                        String error = msg.getData().getString(Share.ERROR);
                        onResult(false, new TaskError(errorCode, error));
                    }
                    else if (msg.getData().containsKey(Share.PROGRESS))
                    {
                        int progress = msg.getData().getInt(Share.PROGRESS);
                        progress(progress);
                    }
                }
            }
        );
	}
    
    private TaskError validate(String filePath, String shareDir)
    {
        TaskError error = error = new TaskError(context, Constants.ERROR_FILE_IS_NOT_FOUND, R.string.error_file_is_not_found);
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
    
    @Override
    public void progress(int progress)
    {
        callback.progress(progress);
    }

    @Override
    public void onResult(boolean result, TaskError error)
    {
        UITool.get().setProgress(context, false);
        callback.onResult(result, error);
    }
}
