package ru.net.serbis.tools.util;

import android.content.*;
import android.os.*;

public class WakeLocker
{
    public void start(Context context)
    {
        try
        {
            PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakeLock = manager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, this.getClass().getName());
            wakeLock.acquire(60000);
        }
        catch(Exception e)
        {
            Log.error(this, e);
        }
    }
}
