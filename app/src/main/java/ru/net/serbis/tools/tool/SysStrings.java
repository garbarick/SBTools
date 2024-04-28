package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.fragment.*;

public class SysStrings extends Tool
{
    @Override
    public void tool()
    {
        new StringsFragment(context);
    }

    @Override
    public int getNameId()
    {
        return R.string.sys_strings;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.tool_sys_strings;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }
}
