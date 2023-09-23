package ru.net.serbis.tools.adapter;

import android.content.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.tool.*;

public class HideToolsAdapter extends ArrayAdapter<String>
{
    private List<Tool> items = new ArrayList<Tool>();

    public HideToolsAdapter(Context context)
    {
        super(context, android.R.layout.simple_list_item_multiple_choice);
        initItems(context);
    }

    private void initItems(Context context)
    {
        initItems(context, Tools.MAIN_TOOLS);
        initItems(context, Tools.SYS_RESOURCES_TOOLS);
    }

    private void initItems(Context context, Tool[] tools)
    {
        for (Tool tool : tools)
        {
            if (tool == Tools.HIDE_TOOLS)
            {
                continue;
            }
            String name = context.getResources().getString(tool.getNameId());
            items.add(tool);
            add(name);
        }
    }
    
    public Tool getTool(int position)
    {
        return items.get(position);
    }
}
