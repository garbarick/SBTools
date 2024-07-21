package ru.net.serbis.tools.activity;

import android.appwidget.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.utils.*;

public class StartToolActivity extends ToolsActivity
{
    private Tool tool;

    @Override
    protected Tool[] getTools()
    {
        int id = getIntent().getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        String key = Constants.WIDGET + id;
        String name = Preferences.get().getString(key, null);
        if (name == null)
        {
            finish();
        }
        tool = Tools.TOOLS_MAP.get(name);
        if (tool == null)
        {
            finish();
            return new Tool[]{};
        }
        return new Tool[]{tool};
    }

    @Override
    protected boolean isHidden(Tool tool)
    {
        return false;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (tool == null)
        {
            finish();
        }
        tool.tool();
    }

    @Override
    public void closeTool()
    {
        finish();
    }
}
