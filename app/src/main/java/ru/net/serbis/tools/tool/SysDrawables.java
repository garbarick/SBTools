package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.fragment.*;

public class SysDrawables extends Tool
{
    @Override
    public void tool()
    {
        new DrawablesFragment(context);
    }

    @Override
    public int getNameId()
    {
        return R.string.sys_drawables;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.tool_sys_drawables;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }
}
