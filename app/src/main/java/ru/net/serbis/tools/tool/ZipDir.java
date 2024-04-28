package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;

public class ZipDir extends Tool implements TaskCallback<Boolean>
{
    @Override
    public void tool()
    {
        disable();
        notification = new NotificationProgress(context, R.string.zip_dir);
        new ZipTask(context, this).execute();
    }

    @Override
    protected void settings()
    {
        new ParamsFragment(context, R.string.settings, Params.ZIP_DIR_PARAMS);
    }

    @Override
    public void progress(int value)
    {
        notification.setProgress(value);
        bar.setProgress(value);
    }

    @Override
    public void onResult(Boolean result, TaskError error)
    {
        if (!result)
        {
            UITool.get().toast(error);
        }
        notification.cancel();
        enable();
        context.closeTool();
    }

    @Override
    public int getNameId()
    {
        return R.string.zip_dir;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.tool_zip_dir;
    }
}
