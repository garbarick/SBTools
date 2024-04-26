package ru.net.serbis.tools.tool;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.util.*;

public abstract class Tool implements View.OnClickListener, PopupMenu.OnMenuItemClickListener
{
    protected int iconId;
    protected int toolId;
    protected int settingsId;
    protected Activity context;
    protected LinearLayout main;
    protected NotificationProgress notification;
    protected ProgressBar bar;
    protected boolean hidden;

    public int[] getButtonIds()
    {
        return new int[]{iconId, toolId, settingsId};
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

    public void setView(View view)
    {
        ImageView icon = UITool.get().findView(view, R.id.icon);
        iconId = icon.generateViewId();
        icon.setId(iconId);
        if (getImageId() > 0)
        {
            icon.setBackgroundResource(getImageId());
        }
        else
        {
            icon.setVisibility(View.GONE);
        }

        Button tool = UITool.get().findView(view, R.id.tool);
        toolId = tool.generateViewId();
        tool.setId(toolId);
        tool.setText(getNameId());

        ImageButton settings = UITool.get().findView(view, R.id.settings);
        settingsId = settings.generateViewId();
        settings.setId(settingsId);
        if (!hasSettings())
        {
            settings.setVisibility(View.GONE);
        }
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

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
        onClick(item.getItemId());
        return false;
    }

    protected void onClick(int id)
    {
        if (id == toolId)
        {
            tool();
        }
        else if (id == settingsId)
        {
            settings();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    }

    public void setHidden(boolean hidden)
    {
        this.hidden = hidden;
    }

    public boolean isHidden()
    {
        return hidden;
    }

    public abstract int getNameId();

    public int getImageId()
    {
        return 0;
    }

    protected boolean hasSettings()
    {
        return true;
    }

    protected void tool()
    {
    }

    protected void settings()
    {
    }
}
