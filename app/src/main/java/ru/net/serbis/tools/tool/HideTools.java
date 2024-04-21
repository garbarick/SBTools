package ru.net.serbis.tools.tool;

import android.app.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.util.*;

public class HideTools extends Tool
{
    public HideTools()
    {
        super(
            R.layout.tool_hide_tools,
            R.id.hide_tools
        );
    }

    @Override
    public void setContext(Activity context)
    {
        super.setContext(context);
        initVisible(Tools.MAIN_TOOLS);
        initVisible(Tools.SYS_RESOURCES_TOOLS);
    }

    private void initVisible(Tool[] tools)
    {
        Set<String> hides = Params.HIDE_TOOLS.getValue();
        for (Tool tool : tools)
        {
            if (tool == Tools.HIDE_TOOLS)
            {
                continue;
            }
            String name = Strings.get().get(tool.getNameId());
            tool.setHidden(hides.contains(name));
        }
    }

    @Override
    protected void onClick(int id)
    {
        switch (id)
        {
            case R.id.hide_tools:
                new HideToolsFragment(context);
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.hide_tools;
    }
}
