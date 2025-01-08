package ru.net.serbis.tools.service;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.view.WindowManager.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.popup.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.tools.view.*;
import ru.net.serbis.utils.*;

public class ScreenFilterService extends Service
{
    private class IncomingHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            switchFilter();
        }
    }

    private Messenger messenger;
    private View filter;

    @Override
    public IBinder onBind(Intent intent)
    {
        return messenger.getBinder();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        messenger = new Messenger(new IncomingHandler());
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    private void switchFilter()
    {
        if (filter != null && filter.isShown())
        {
            filterOff();
        }
        else
        {
            filterOn();
        }
    }

    private void filterOn()
    {
        WindowManager manager = SysTool.get().getService(WINDOW_SERVICE);
        filter = createView();
        LayoutParams params = createParams((FilterLayout) filter);
        manager.addView(filter, params);
        showBrightnessWindow();
    }

    private void filterOff()
    {
        WindowManager manager = SysTool.get().getService(WINDOW_SERVICE);
        manager.removeView(filter);
        filter = null;
    }

    private View createView()
    {
        View view = LayoutInflater.from(this).inflate(R.layout.filter, null);
        ViewTool.get().setColorTransparent(view, Params.BRIGHTNESS.getValue(), Params.COLOR_FILTER.getValue());
        return view;
    }

    private LayoutParams createParams(FilterLayout view)
    {
        LayoutParams params = new LayoutParams();
        params.flags = LayoutParams.FLAG_NOT_TOUCHABLE |
            LayoutParams.FLAG_LAYOUT_NO_LIMITS |
            LayoutParams.FLAG_NOT_FOCUSABLE |
            LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.format = PixelFormat.TRANSLUCENT;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        {
            params.type = LayoutParams.TYPE_APPLICATION_OVERLAY;            
        }
        else
        {
            params.type = LayoutParams.TYPE_PHONE;            
        }

        Point size = view.getSize();
        params.x = 0;
        params.y = 0;
        params.width = size.x;
        params.height = size.y;

        return params;
    }

    private void showBrightnessWindow()
    {
        if (Params.SHOW_BRIGHTNESS.getValue())
        {
            filter.post(
                new Runnable()
                {
                    public void run()
                    {
                        new BrightnessPopup(filter).show();
                    }
                }
            );
        }
    }
}
