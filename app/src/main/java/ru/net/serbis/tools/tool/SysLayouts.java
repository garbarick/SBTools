package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.fragment.*;

public class SysLayouts extends Tool
{
    public SysLayouts()
    {
        super(
            R.layout.tool_sys_layouts,
            R.id.sys_layouts);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.sys_layouts:
                new XmlFragment(context);
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.sys_layouts;
    }
}
