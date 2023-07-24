package ru.net.serbis.tools.activity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.tools.util.*;

public abstract class ToolsActivity extends Activity
{
    private LinearLayout main;
    private ToolsAdapter adapter;

    @Override
    protected void onCreate(Bundle state)
    {
        super.onCreate(state);

        setContentView(R.layout.main);
        main = UITool.get().findView(this, R.id.main);
        adapter = new ToolsAdapter(this, main, getTools());

        if (UITool.get().isProgress(this))
        {
            main.setEnabled(false);
        }
    }

    protected abstract Tool[] getTools();

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
}
