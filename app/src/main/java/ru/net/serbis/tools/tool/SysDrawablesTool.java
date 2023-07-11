package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.dialog.*;

public class SysDrawablesTool extends Tool
{
    public SysDrawablesTool()
    {
        super(
            R.layout.tool_sys_drawables,
            R.id.sys_drawables);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.sys_drawables:
                new SysDrawables(context);
                break;
        }
    }
}
