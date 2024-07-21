package ru.net.serbis.tools.activity;

import android.app.*;
import android.appwidget.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.tools.widget.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public class ConfigStartTool extends Activity implements View.OnClickListener
{
    private ListView tools;
    private ChooseToolAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_start_tool);
        setResult(RESULT_CANCELED);

        Button create = UITool.get().findView(this, R.id.create);
        create.setOnClickListener(this);

        tools = UITool.get().findView(this, R.id.tools);
        tools.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter = new ChooseToolAdapter(this);
        tools.setAdapter(adapter);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.create:
                create();
                break;
        }
    }

    private void create()
    {
        int position = tools.getCheckedItemPosition();
        if (position == -1)
        {
            return;
        }
        int id = getIntent().getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        Tool tool = adapter.getItem(position);
        String name = Strings.get().get(tool.getNameId());
        String key = Constants.WIDGET + id;
        Preferences.get().setString(key, name);

        Widget widget = getWidget(id);
        widget.onUpdate(this, AppWidgetManager.getInstance(this), id);

        Intent intent = new Intent();
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
        setResult(RESULT_OK, intent);

        finish();
    }

    private Widget getWidget(int id)
    {
        try
        {
            AppWidgetManager widgetManager = AppWidgetManager.getInstance(this);
            Class clazz = Class.forName(widgetManager.getAppWidgetInfo(id).provider.getClassName());
            return (Widget) clazz.newInstance();
        }
        catch (Throwable e)
        {
            Log.error(this, e);
            return null;
        }
    }
}
