package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.fragment.*;

public class Activities extends Tool
{
    @Override
    public void tool()
    {
        new ActivitiesFragment(context);
    }

    @Override
    public int getNameId()
    {
        return R.string.activities;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.tool_activities;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }
}
