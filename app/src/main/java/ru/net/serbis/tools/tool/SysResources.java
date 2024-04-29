package ru.net.serbis.tools.tool;

import android.content.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.fragment.*;

public class SysResources extends NoImageTool
{
    @Override
    public void tool()
    {
        Intent intent = new Intent(context, SysResourcesActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void settings()
    {
        new ParamsFragment(context, R.string.settings, Params.SYS_RESOURSES);
    }

    @Override
    public int getNameId()
    {
        return R.string.sys_resources;
    }
}
