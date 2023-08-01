package ru.net.serbis.tools.tool;

import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;

public class Activities extends Tool implements TaskCallback<Collection<ActivityItem>>
{
    public Activities()
    {
        super(R.layout.tool_activities, R.id.activities);
    }

    @Override
    protected void onClick(int id)
    {
        switch (id)
        {
            case R.id.activities:
                showActivities();
                break;
        }
    }

    private void showActivities()
    {
        disable();
        notification = new NotificationProgress(context, R.string.activities);
        new PackagesLoader(context, this).execute();
    }

    @Override
    public void progress(int progress)
    {
        notification.setProgress(progress);
        bar.setProgress(progress);
    }

    @Override
    public void onResult(Collection<ActivityItem> items, TaskError error)
    {
        notification.cancel();
        enable();
        if (error == null)
        {
            new ActivitiesDialog(context, items);
            return;
        }
        UITool.get().toast(context, error);
    }
}
