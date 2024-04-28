package ru.net.serbis.tools.widget;

import android.appwidget.*;
import android.content.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.tools.util.*;

public class StartTool extends Widget
{
    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int id)
    {
        RemoteViews views = getRemoteView(context, manager, id);
        if (views == null)
        {
            return;
        }
        String key = Constants.WIDGET + id;
        String name = Preferences.get().getString(key, null);
        if (name == null)
        {
            return;
        }
        Tool tool = Tools.TOOLS_MAP.get(name);
        if (tool == null)
        {
            return;
        }

        views.setImageViewResource(R.id.start, tool.getImageId());
        views.setOnClickPendingIntent(R.id.start, getPendingIntent(context, Constants.START, id));
        manager.updateAppWidget(id, views);
    }

    @Override
    protected void onDeleted(Context context, int id)
    {
        String key = Constants.WIDGET + id;
        Preferences.get().setString(key, null);
    }

    @Override
    public void onReceive(Context context, Intent intent, int id)
    {
        if (Constants.START.equals(intent.getAction()))
        {
            Intent start = getActionIntent(context, StartToolActivity.class, Constants.START, id);
            start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            start.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(start);
        }
    }
}
