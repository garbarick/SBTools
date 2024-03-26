package ru.net.serbis.tools.util;

import android.content.*;
import ru.net.serbis.tools.*;

public class Util
{
    protected Context context;
    protected App app;

    public void set(Context context)
    {
        this.context = context;
        app = (App) context.getApplicationContext();
    }
}
