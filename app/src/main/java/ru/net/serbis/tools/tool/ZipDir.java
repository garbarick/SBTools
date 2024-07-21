package ru.net.serbis.tools.tool;

import java.io.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.bean.*;

public class ZipDir extends Tool implements TaskCallback<Boolean>
{
    @Override
    public void tool()
    {
        disable();
        notification = new NotificationProgress(context, R.string.zip_dir, getImageId());

        ZipParams params = new ZipParams();
        params.dir = new File(Params.DIRECTORY.getValue());
        params.result = new File(params.dir, Params.ZIP_NAME.getValue());
        params.compression = Params.COMPRESSION.getValue().getLevel();
        params.deleteSourceFiles = Params.DELETE_SOURCE_FOLRS.getValue();
        params.bufferSize = Params.BUFFER_SIZE.getValue();

        new ZipTask(context, this).execute(params);
    }

    @Override
    protected Param[] getParams()
    {
        return Params.ZIP_DIR_PARAMS;
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
