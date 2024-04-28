package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.fragment.*;

public class CheckPermissions extends Tool
{

    @Override
    public void tool()
    {
        new PermissionsFragment(context);
    }

    @Override
    public int getNameId()
    {
        return R.string.check_permissions;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }
}
