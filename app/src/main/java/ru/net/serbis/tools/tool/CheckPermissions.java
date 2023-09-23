package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.fragment.*;

public class CheckPermissions extends Tool
{
    public CheckPermissions()
    {
        super(
            R.layout.tool_check_permissions,
            R.id.check_permissions);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.check_permissions:
                new PermissionsFragment(context);
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.check_permissions;
    }
}
