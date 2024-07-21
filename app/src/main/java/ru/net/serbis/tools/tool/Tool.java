package ru.net.serbis.tools.tool;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.utils.*;

public abstract class Tool implements View.OnClickListener, PopupMenu.OnMenuItemClickListener
{
    protected int iconId;
    protected int toolId;
    protected int settingsId;
    protected ToolsActivity context;
    protected LinearLayout main;
    protected NotificationProgress notification;
    protected ProgressBar bar;
    protected boolean hidden;
    protected ParamsFragment paramsDialog;

    public int[] getButtonIds()
    {
        return new int[]{iconId, toolId, settingsId};
    }

    public void setContext(ToolsActivity context)
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
        initImage(icon);

        Button tool = UITool.get().findView(view, R.id.tool);
        initToolButton(tool);

        ImageButton settings = UITool.get().findView(view, R.id.settings);
        initSettingsButton(settings);
    }

    protected void initImage(ImageView icon)
    {
        iconId = icon.generateViewId();
        icon.setId(iconId);
        if (getImageId() > 0)
        {
            icon.setBackgroundResource(getImageId());
        }
    }

    protected void initToolButton(Button tool)
    {
        toolId = tool.generateViewId();
        tool.setId(toolId);
        tool.setText(getNameId());
    }

    protected void initSettingsButton(ImageButton settings)
    {
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

    public abstract int getImageId()

    protected boolean hasSettings()
    {
        return true;
    }

    protected Param[] getParams()
    {
        return new Param[]{};
    }

    public void tool()
    {
        UITool.get().notImplementedYet();
    }

    protected void settings()
    {
        paramsDialog = new ParamsFragment(context, R.string.settings, getParams());
    }
}
