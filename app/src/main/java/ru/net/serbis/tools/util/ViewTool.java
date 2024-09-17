package ru.net.serbis.tools.util;

import android.app.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public class ViewTool
{
    private static ViewTool instance = new ViewTool();

    public static ViewTool get()
    {
        return instance;
    }

    public void setColorTransparent(Activity context, int transparent, int color)
    {
        Window window = context.getWindow();
        window.setBackgroundDrawable(getDrawableTransparent(transparent, color));
    }

    public void setColorTransparent(View view, int transparent, int color)
    {
        view.setBackgroundDrawable(getDrawableTransparent(transparent, color));
    }

    public Drawable getDrawableTransparent(int transparent, int color)
    {
        return new ColorDrawable(getColorTransparent(transparent, color));
    }

    public int getColorTransparent(int transparent, int color)
    {
        return Color.argb(255 - transparent, Color.red(color), Color.green(color), Color.blue(color));
    }
}
