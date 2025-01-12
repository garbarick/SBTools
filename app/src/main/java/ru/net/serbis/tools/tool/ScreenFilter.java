package ru.net.serbis.tools.tool;

import android.content.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.param.*;

public class ScreenFilter extends Tool
{
    @Override
    public int getNameId()
    {
        return R.string.screen_filter;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.screen_filter;
    }

    @Override
    protected Param[] getParams()
    {
        return Params.SCREEN_FILTER_PARAMS;
    }

    @Override
    public void tool()
    {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1 &&
            !Settings.canDrawOverlays(context))
        {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
            context.startActivityForResult(intent, 1234);
            return;
        }

        App app = (App) context.getApplicationContext();
        app.sendToScreenFilter();
    }

    @Override
    public boolean closeParent()
    {
        return true;
    }
}
