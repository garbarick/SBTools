package ru.net.serbis.tools.adapter;

import android.app.*;
import android.view.*;
import android.widget.*;
import java.lang.reflect.*;
import java.util.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.tools.util.*;

public class ToolsAdapter
{
    private List<Tool> tools = new ArrayList<Tool>();

    public ToolsAdapter(Activity context, LinearLayout main)
    {
        initTools();
        initViews(context, main);
    }

    private void initTools()
    {
        for (Field field : Tools.class.getFields())
        {
            try
            {
                Tool tool = (Tool) field.get(null);
                tools.add(tool);
            }
            catch (Exception e)
            {
                Log.error(this, e);
            }
        }
    }

    private void initViews(Activity context, LinearLayout main)
    {
        int index = 0;
        for (Tool tool : tools)
        {
            View view = LayoutInflater.from(context).inflate(tool.getLayoutId(), main, false);
            tool.setContext(context);
            tool.setMain(main);
            main.addView(view, index);
            UITool.initButtons(context, tool, tool.getButtonIds());
        }
    }
}
