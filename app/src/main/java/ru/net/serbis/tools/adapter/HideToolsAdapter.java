package ru.net.serbis.tools.adapter;

import android.content.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.utils.*;

public class HideToolsAdapter extends ArrayAdapter<String>
{
    private List<Tool> items = new ArrayList<Tool>();

    public HideToolsAdapter(Context context)
    {
        super(context, android.R.layout.simple_list_item_multiple_choice);
        initItems();
    }

    private void initItems()
    {
        initItems(Tools.MAIN_TOOLS);
        initItems(Tools.SYS_RESOURCES_TOOLS);
    }

    private void initItems(Tool[] tools)
    {
        for (Tool tool : tools)
        {
            if (tool == Tools.HIDE_TOOLS)
            {
                continue;
            }
            String name = Strings.get().get(tool.getNameId());
            items.add(tool);
            add(name);
        }
    }
    
    public Tool getTool(int position)
    {
        return items.get(position);
    }
}
