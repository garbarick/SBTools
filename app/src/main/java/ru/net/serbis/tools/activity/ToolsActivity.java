package ru.net.serbis.tools.activity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.utils.*;

public abstract class ToolsActivity extends Activity
{
    private LinearLayout main;
    private ToolsAdapter adapter;

    @Override
    protected void onCreate(Bundle state)
    {
        super.onCreate(state);
        init();
    }

    public void init()
    {
        setContentView(R.layout.main);
        main = UITool.get().findView(this, R.id.main);
        adapter = new ToolsAdapter(this, main, getVisibleTools());

        if (UITool.get().isProgress())
        {
            main.setEnabled(false);
        }
    }

    protected abstract Tool[] getTools();
    
    private Tool[] getVisibleTools()
    {
        List<Tool> result = new ArrayList<Tool>();
        Tool[] tools = getTools();
        for (Tool tool : tools)
        {
            tool.setContext(this);
        }
        for (Tool tool : tools)
        {
            if (!isHidden(tool))
            {
                result.add(tool);
            }
        }
        return result.toArray(new Tool[result.size()]);
    }

    protected boolean isHidden(Tool tool)
    {
        return tool.isHidden();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        adapter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed()
    {
        if (main.isEnabled())
        {
            super.onBackPressed();
        }
    }

    public void closeTool()
    {
    }
}
