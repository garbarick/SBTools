package ru.net.serbis.tools.notification;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.tools.receiver.*;

public class NotificationProgress extends Notification.Builder
{
    private int id = new Random(new Date().getTime()).nextInt();
    private Context context;
    private int textId;
    private NotifyType type;
    private NotificationManager manager;
    private RemoteViews views;
    private boolean old;

    public NotificationProgress(Context context, int textId)
    {
        super(context);
        old = Build.VERSION.SDK_INT < Build.VERSION_CODES.N;
        this.context = context;
        this.textId = textId;
        type = Params.NOTIFY_TYPE.getValue(context);
        setSmallIcon(R.drawable.app);
        setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, Main.class), PendingIntent.FLAG_UPDATE_CURRENT));
        setOngoing(true);
        manager = SysTool.get().getService(context, Context.NOTIFICATION_SERVICE);
        setContent(0, true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = context.getResources().getString(R.string.progress);
            setChannelId(channelId);
            NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
        }
    }

    public void setProgress(int progress)
    {
        setContent(progress, false);
        manager.notify(id, build());
    }

    public void cancel()
    {
        manager.cancel(id);
    }

    public void setContent(int progress, boolean init)
    {
        switch (type)
        {
            case STANDARD:
                setStandardContent(progress, init);
                break;
            case CUSTOM:
                setCustomContent(progress, init);
                break;
        }
    }

    private void setStandardContent(int progress, boolean init)
    {
        if (init)
        {
            setContentText(context.getResources().getString(textId));
        }
        setContentTitle(progress + " %");
        setProgress(100, progress, false);
    }

    private void setCustomContent(int progress, boolean init)
    {
        if (init)
        {
            views = new RemoteViews(context.getPackageName(), R.layout.progress);
            views.setImageViewResource(R.id.icon, R.drawable.app);
            views.setTextViewText(R.id.name, context.getResources().getString(R.string.app));
            views.setTextViewText(R.id.text, context.getResources().getString(textId));
            views.setOnClickPendingIntent(R.id.button, getAction(Actions.TEST));
            setContent(views);
        }
        views.setTextViewText(R.id.title, progress + " %");
        views.setProgressBar(R.id.progress, 100, progress, false);
    }

    private PendingIntent getAction(String action)
    {
        Intent intent = new Intent(context, ActionsReceiver.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public Notification build()
    {
        Notification result = super.build();
        if (old && NotifyType.CUSTOM.equals(type))
        {
            result.bigContentView = views;
        }
        return result;
    }
}
