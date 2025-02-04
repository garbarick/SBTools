package ru.net.serbis.tools.adapter;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public class ToolsAdapter
{
    private Tool[] tools;

    public ToolsAdapter(Activity context, LinearLayout main, Tool[] tools)
    {
        this.tools = tools;
        initViews(context, main);
    }

    private void initViews(Activity context, LinearLayout main)
    {
        int index = 0;
        for (Tool tool : tools)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.tool, main, false);
            tool.setMain(main);
            tool.setView(view);
            main.addView(view, index);
            UITool.get().initButtons(context, tool, tool.getButtonIds());
            index ++;
        }
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        for (Tool tool : tools)
        {
            tool.onActivityResult(requestCode, resultCode, data);
        }
    }
}
