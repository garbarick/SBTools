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
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public class NotificationProgress extends Notification.Builder
{
    private int id = new Random(new Date().getTime()).nextInt();
    private Context context;
    private int textId;
    private int iconId;
    private NotifyType type;
    private NotificationManager manager;
    private RemoteViews views;
    private boolean old;
    private int oldProgress = -1;
    private WakeLocker wakeLocker = new WakeLocker();

    public NotificationProgress(Context context, int textId, int iconId)
    {
        super(context);
        old = Build.VERSION.SDK_INT < Build.VERSION_CODES.N;
        this.context = context;
        this.textId = textId;
        this.iconId = iconId;
        type = Params.NOTIFY_TYPE.getValue();
        setSmallIcon(iconId);
        setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, Main.class), PendingIntent.FLAG_UPDATE_CURRENT));
        setOngoing(true);
        manager = SysTool.get().getService(Context.NOTIFICATION_SERVICE);
        setContent(0, true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = Strings.get().get(R.string.progress);
            setChannelId(channelId);
            NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
        }
    }

    public NotificationProgress(Context context, int textId)
    {
        this(context, textId, R.drawable.app);
    }

    synchronized
    public void setProgress(int progress)
    {
        try
        {
            if (oldProgress == progress)
            {
                return;
            }
            oldProgress = progress;
            wakeLocker.start(context);
            setContent(progress, false);
            manager.notify(id, build());
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
    }

    public void cancel()
    {
        manager.cancelAll();
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
            setContentText(Strings.get().get(textId));
        }
        setContentTitle(progress + " %");
        setProgress(100, progress, false);
    }

    private void setCustomContent(int progress, boolean init)
    {
        if (init)
        {
            views = new RemoteViews(context.getPackageName(), R.layout.progress);
            views.setImageViewResource(R.id.icon, iconId);
            views.setTextViewText(R.id.name, Strings.get().get(R.string.app));
            views.setTextViewText(R.id.text, Strings.get().get(textId));
            setContent(views);
        }
        views.setTextViewText(R.id.title, progress + " %");
        views.setProgressBar(R.id.progress, 100, progress, false);
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
