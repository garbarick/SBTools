package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;

public class SysDrawables extends Tool
{
    public SysDrawables()
    {
        super(
            R.layout.tool_sys_drawables,
            R.id.sys_drawables,
            R.id.sys_drawables_set);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.sys_drawables:
                new DrawablesDialog(context);
                break;

            case R.id.sys_drawables_set:
                new ParamsDialog(context, R.string.sys_drawables_set, Params.SYS_DRAWABLES_PARAMS);
                break;
        }
    }
}
