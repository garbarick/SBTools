package ru.net.serbis.tools.tool;

import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;

public class ProgressInNotification extends Tool implements TaskCallback
{
    private NotificationProgress notification;

    public ProgressInNotification()
    {
        super(R.layout.tool_progress_in_notification, R.id.progress_in_notification);
    }

    @Override
    protected void onClick(int id)
    {
        switch (id)
        {
            case R.id.progress_in_notification:
                makeProgress();
                break;
        }
    }

    private void makeProgress()
    {
        disable();
        notification = new NotificationProgress(context, R.string.in_progress);
        new ProgressTask(context, this).execute(1000, 10);
    }

    @Override
    public void progress(int value)
    {
        notification.setProgress(value);
        ProgressBar bar = UITool.findView(context, R.id.progress);
        bar.setProgress(value);
    }

    @Override
    public void onResult(boolean result, TaskError error)
    {
        notification.cancel();
        enable();
    }
}
