package ru.net.serbis.tools.tool;

import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.util.*;

public class HideTools extends NoImageTool
{
    @Override
    public void setContext(ToolsActivity context)
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
    public void tool()
    {
        new HideToolsFragment(context);
    }

    @Override
    public int getNameId()
    {
        return R.string.hide_tools;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }
}
