package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;

public class SysStrings extends Tool
{
    public SysStrings()
    {
        super(
            R.layout.tool_sys_strings,
            R.id.sys_strings,
            R.id.sys_strings_set);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.sys_strings:
                new StringsDialog(context);
                break;

            case R.id.sys_strings_set:
                new ParamsDialog(context, R.string.sys_strings_set, Params.SYS_STRINGS_PARAMS);
                break;
        }
    }
}
