package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.fragment.*;

public class SysLayouts extends Tool
{
    @Override
    public void tool()
    {
        new XmlFragment(context);
    }

    @Override
    public int getNameId()
    {
        return R.string.sys_layouts;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.tool_sys_layouts;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }
}
