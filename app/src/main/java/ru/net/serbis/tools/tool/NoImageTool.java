package ru.net.serbis.tools.tool;

import android.view.*;
import android.widget.*;

public abstract class NoImageTool extends Tool
{
    @Override
    public int getImageId()
    {
        return 0;
    }

    @Override
    protected void initImage(ImageView icon)
    {
        super.initImage(icon);
        icon.setVisibility(View.GONE);
    }
}
