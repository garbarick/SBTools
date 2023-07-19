package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;

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
                new ColorsDialog(context);
                break;

            case R.id.sys_colors_set:
                new ParamsDialog(context, R.string.sys_colors_set, Params.SYS_COLORS_PARAMS);
                break;
        }
    }
}
