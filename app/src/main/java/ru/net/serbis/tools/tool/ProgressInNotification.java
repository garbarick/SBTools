package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.utils.bean.*;
import ru.net.serbis.utils.param.*;

public class ProgressInNotification extends NoImageTool implements TaskCallback<Boolean>
{
    @Override
    public void tool()
    {
        disable();
        Resource icon = Params.NOTIFY_ICON.getValue();
        notification = new NotificationProgress(context, R.string.in_progress, icon.getId());
        new ProgressTask(context, this).execute(1000, 10);
    }

    @Override
    protected Param[] getParams()
    {
        return Params.NOTIFICATION_PARAMS;
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

    @Override
    public int getNameId()
    {
        return R.string.progress_in_notification;
    }
}
