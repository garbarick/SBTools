package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import java.lang.reflect.*;
import java.util.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.tool.*;
import ru.net.serbis.tools.tool.*;

public class ToolsAdapter
{
    public ToolsAdapter(Context context, LinearLayout parent)
    {
        int index = 0;
        for (Tool tool : getTools())
        {
            try
            {
                View view = LayoutInflater.from(context).inflate(tool.getLayoutId(), parent, false);
                parent.addView(view, index);
                index ++;
            }
            catch (Exception e)
            {
                Log.error(this, e);
            }
        }
    }
    
    private List<Tool> getTools()
    {
        List<Tool> result = new ArrayList<Tool>();
        for (Field field : Tools.class.getFields())
        {
            try
            {
                Tool tool = (Tool) field.get(null);
                result.add(tool);
            }
            catch (Exception e)
            {
                Log.error(this, e);
            }
        }
        return result;
    }

    public List<Integer> getButtonIds()
    {
        List<Integer> result = new ArrayList<Integer>();
        for (Tool tool : getTools())
        {
            result.addAll(tool.getButtonIds());
        }
        return result;
    }
}
