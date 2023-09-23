package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.fragment.*;

public class SysLayouts extends Tool
{
    public SysLayouts()
    {
        super(
            R.layout.tool_sys_layouts,
            R.id.sys_layouts,
            R.id.sys_layouts_set);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.sys_layouts:
                new XmlFragment(context);
                break;

            case R.id.sys_layouts_set:
                new ParamsFragment(context, R.string.sys_layouts_set, Params.SYS_XML_PARAMS);
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.sys_layouts;
    }
}
