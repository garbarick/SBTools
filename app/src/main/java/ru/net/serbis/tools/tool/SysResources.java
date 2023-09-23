package ru.net.serbis.tools.tool;

import android.content.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;

public class SysResources extends Tool
{
    public SysResources()
    {
        super(R.layout.tool_sys_resources, R.id.sys_resources);
    }

    @Override
    protected void onClick(int id)
    {
        switch (id)
        {
            case R.id.sys_resources:
                startActivity();
                break;
        }
    }

    private void startActivity()
    {
        Intent intent = new Intent(context, SysResourcesActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getNameId()
    {
        return R.string.sys_resources;
    }
}
