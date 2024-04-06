package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public class PreferencesTool extends Tool
{
    public PreferencesTool()
    {
        super(
            R.layout.tool_preferences,
            R.id.preferences);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.preferences:
                UITool.get().notImplementedYet();
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.preferences;
    }
}
