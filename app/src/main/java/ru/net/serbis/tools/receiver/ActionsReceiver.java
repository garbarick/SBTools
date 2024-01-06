package ru.net.serbis.tools.receiver;

import android.content.*;
import ru.net.serbis.tools.util.*;

public class ActionsReceiver extends BroadcastReceiver 
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        UITool.get().toast(context, "action:" + action);
    }
}
