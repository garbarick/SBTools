package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.fragment.*;

public class Activities extends Tool
{
    public Activities()
    {
        super(R.layout.tool_activities, R.id.activities);
    }

    @Override
    protected void onClick(int id)
    {
        switch (id)
        {
            case R.id.activities:
                new ActivitiesFragment(context);
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.activities;
    }
}
