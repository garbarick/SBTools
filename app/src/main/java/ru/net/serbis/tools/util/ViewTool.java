package ru.net.serbis.tools.util;

import android.app.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.utils.*;

public class ViewTool extends Util
{
    private static ViewTool instance = new ViewTool();

    public static ViewTool get()
    {
        return instance;
    }

    public void setSandwitchView(Button button)
    {
        Drawable sandwitch = context.getResources().getDrawable(R.drawable.sandwitch);
        sandwitch.setBounds(28, 0, 92, 64);
        button.setCompoundDrawables(sandwitch, null, null, null);
    }

    public void setColorTransparent(Activity context, int transparent)
    {
        Window window = context.getWindow();
        window.setBackgroundDrawable(getDrawableTransparent(transparent));
    }

    public void setColorTransparent(View view, int transparent)
    {
        view.setBackgroundDrawable(getDrawableTransparent(transparent));
    }

    public Drawable getDrawableTransparent(int transparent)
    {
        return new ColorDrawable(getColorTransparent(transparent));
    }

    public int getColorTransparent(int transparent)
    {
        return Color.argb(255 - transparent, 50, 50, 50);
    }
}
