package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.fragment.*;

public class SysThemes extends Tool
{
    @Override
    public void tool()
    {
        new ThemesFragment(context);
    }

    @Override
    public int getNameId()
    {
        return R.string.sys_themes;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.tool_sys_themes;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }
}
