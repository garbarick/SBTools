package ru.net.serbis.tools.tool;

import android.content.*;
import android.graphics.*;
import android.net.*;
import android.provider.*;
import android.view.*;
import android.view.WindowManager.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.util.*;

public class ScreenFilter extends Tool
{
    private View view;

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
        if (!Settings.canDrawOverlays(context))
        {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
            context.startActivityForResult(intent, 1234);
            return;
        }
        WindowManager manager = context.getWindowManager();
        if (view == null)
        {
            view = createView();
            LayoutParams params = createParams();
            manager.addView(view, params);
        }
        else
        {
            manager.removeView(view);
            view = null;
        }
    }

    @Override
    public boolean closeParent()
    {
        return true;
    }

    private View createView()
    {
        View view = LayoutInflater.from(context).inflate(R.layout.filter, null);
        ViewTool.get().setColorTransparent(view, Params.BRIGHTNESS.getValue());
        return view;
    }

    private LayoutParams createParams()
    {
        LayoutParams params = new LayoutParams();
        params.flags = LayoutParams.FLAG_NOT_TOUCHABLE |
            LayoutParams.FLAG_LAYOUT_NO_LIMITS |
            LayoutParams.FLAG_NOT_FOCUSABLE |
            LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = LayoutParams.TYPE_APPLICATION_OVERLAY;
        return params;
    }
}
