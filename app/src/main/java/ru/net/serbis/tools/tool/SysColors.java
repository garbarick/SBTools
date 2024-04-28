package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.fragment.*;

public class SysColors extends Tool
{
    @Override
    public void tool()
    {
        new ColorsFragment(context);
    }

    @Override
    public int getNameId()
    {
        return R.string.sys_colors;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.tool_sys_colors;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }
}
