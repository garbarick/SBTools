package ru.net.serbis.tools.widget;

import android.app.*;
import android.appwidget.*;
import android.content.*;
import android.net.*;
import android.widget.*;

public abstract class Widget extends AppWidgetProvider
{
    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] ids)
    {
        super.onUpdate(context, manager, ids);
        for (int id : ids)
        {
            onUpdate(context, manager, id);
        }
    }

    public abstract void onUpdate(Context context, AppWidgetManager manager, int id)

    @Override
    public void onDeleted(Context context, int[] ids)
    {
        super.onDeleted(context, ids);
        for (int id : ids)
        {
            onDeleted(context, id);
        }
    }

    protected abstract void onDeleted(Context context, int id)

    protected RemoteViews getRemoteView(Context context, AppWidgetManager manager, int id)
    {
        AppWidgetProviderInfo info = manager.getAppWidgetInfo(id);
        if (info == null)
        {
            return null;
        }
        int layoutId = info.initialLayout;
        return new RemoteViews(context.getPackageName(), layoutId);
    }

    protected PendingIntent getPendingIntent(Context context, String action, int id)
    {
        Intent intent = getActionIntent(context, getClass(), action, id);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    protected Intent getActionIntent(Context context, Class<?> clazz, String action, int id)
    {
        Intent intent = new Intent(context, clazz);
        intent.setAction(action);
        intent.setData(getUri(action, id));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
        return intent;
    }

    protected Uri getUri(String action, int id)
    {
        return Uri.fromParts(action, getClass().getName(), String.valueOf(id));
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        super.onReceive(context, intent);

        int id = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        if (AppWidgetManager.INVALID_APPWIDGET_ID == id)
        {
            return;
        }
        onReceive(context, intent, id);
    }

    protected void onReceive(Context context, Intent intent, int id)
    {
    }
}
