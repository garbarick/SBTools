package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.dialog.*;

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
                new Permissions(context);
                break;
        }
    }
}
