package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.fragment.*;

public class SysColors extends Tool
{
    public SysColors()
    {
        super(
            R.layout.tool_sys_colors,
            R.id.sys_colors,
            R.id.sys_colors_set);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.sys_colors:
                new ColorsFragment(context);
                break;

            case R.id.sys_colors_set:
                new ParamsFragment(context, R.string.settings, Params.SYS_COLORS_PARAMS);
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.sys_colors;
    }
}
