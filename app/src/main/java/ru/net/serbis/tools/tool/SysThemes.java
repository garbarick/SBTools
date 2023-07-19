package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;

public class SysThemes extends Tool
{
    public SysThemes()
    {
        super(
            R.layout.tool_sys_themes,
            R.id.sys_themes,
            R.id.sys_themes_set);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.sys_themes:
                new ThemesDialog(context);
                break;

            case R.id.sys_themes_set:
                new ParamsDialog(context, R.string.sys_themes_set, Params.SYS_THEMES_PARAMS);
                break;
        }
    }
}
