package ru.net.serbis.tools.service;

import android.app.*;
import android.content.*;
import android.os.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.connection.*;
import ru.net.serbis.utils.*;

public class ScreenFilterConnection extends ExtConnection
{
    boolean sendAfterConnect;

    public ScreenFilterConnection(Application app)
    {
        super(app);
    }

    @Override
    protected String packageName()
    {
        return App.class.getPackage().getName();
    }

    @Override
    protected String serviceName()
    {
        return ScreenFilterService.class.getName();
    }

    public void send()
    {
        try
        {
            Message msg = Message.obtain(null, 0, 0, 0);
            getService().send(msg);
        }
        catch (RemoteException e)
        {
            Log.error(this, e);
        }
    }

    public void bindAndSend()
    {
        if (isBound())
        {
            send();
        }
        else
        {
            sendAfterConnect = true;
            bind();
        }
    }

    @Override
    public void onServiceConnected(ComponentName classsName, IBinder binder)
    {
        super.onServiceConnected(classsName, binder);
        if (sendAfterConnect)
        {
            send();
            sendAfterConnect = false;
        }
    }
}
