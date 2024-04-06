package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;

public class ZipDir extends Tool implements TaskCallback<Boolean>
{
    public ZipDir()
    {
        super(
            R.layout.tool_zip_dir,
            R.id.zip_dir,
            R.id.zip_dir_set);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.zip_dir:
                zipDir();
                break;

            case R.id.zip_dir_set:
                new ParamsFragment(context, R.string.settings, Params.ZIP_DIR_PARAMS);
                break;
        }
    }

    private void zipDir()
    {
        disable();
        notification = new NotificationProgress(context, R.string.zip_dir);
        new ZipTask(context, this).execute();
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
    }

    @Override
    public int getNameId()
    {
        return R.string.zip_dir;
    }
}
