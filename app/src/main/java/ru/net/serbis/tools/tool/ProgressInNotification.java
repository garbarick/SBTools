package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;

public class ProgressInNotification extends Tool implements TaskCallback<Boolean>
{
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
        bar.setProgress(value);
    }

    @Override
    public void onResult(Boolean result, TaskError error)
    {
        notification.cancel();
        enable();
    }
}