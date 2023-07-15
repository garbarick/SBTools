package ru.net.serbis.tools.extension.share;

import android.content.*;
import android.os.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.connection.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.tools.data.*;

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

    public void uploadFile(final String filePath, final String shareDir, final TaskCallback callback)
    {
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
                        callback.onResult(true, null);
                    }
                    else if (msg.getData().containsKey(Share.ERROR) &&
                             msg.getData().containsKey(Share.ERROR_CODE))
                    {
                        int errorCode = msg.getData().getInt(Share.ERROR_CODE);
                        String error = msg.getData().getString(Share.ERROR);
                        callback.onResult(false, new TaskError(errorCode, error));
                    }
                    else if (msg.getData().containsKey(Share.PROGRESS))
                    {
                        int progress = msg.getData().getInt(Share.PROGRESS);
                        callback.progress(progress);
                    }
                }
            }
        );
	}
}
