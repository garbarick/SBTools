package ru.net.serbis.tools.util;

import android.graphics.drawable.*;
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
}
