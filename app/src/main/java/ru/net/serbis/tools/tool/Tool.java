package ru.net.serbis.tools.tool;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.util.*;

public abstract class Tool implements View.OnClickListener
{
    private int layoutId;
    private int[] buttonIds;
    protected Activity context;
    protected LinearLayout main;
    protected NotificationProgress notification;
    protected ProgressBar bar;

    public Tool(int layoutId, int ... buttonIds)
    {
        this.layoutId = layoutId;
        this.buttonIds = buttonIds;
    }

    public int getLayoutId()
    {
        return layoutId;
    }

    public int[] getButtonIds()
    {
        return buttonIds;
    }
    
    public void setContext(Activity context)
    {
        this.context = context;
        bar = UITool.get().findView(context, R.id.progress);
    }

    public void setMain(LinearLayout main)
    {
        this.main = main;
    }

    protected boolean isEnabled()
    {
        return main.isEnabled();
    }

    protected void enable()
    {
        main.setEnabled(true);
    }

    protected void disable()
    {
        main.setEnabled(false);
    }

    @Override
    public void onClick(View view)
    {
        if (isEnabled())
        {
            onClick(view.getId());
        }
    }

    protected abstract void onClick(int id);
    
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    }
}
