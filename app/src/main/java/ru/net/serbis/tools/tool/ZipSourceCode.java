package ru.net.serbis.tools.tool;

import java.io.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;
import java.util.regex.*;

public class ZipSourceCode extends NoImageTool implements TaskCallback<Boolean>
{
    @Override
    public int getNameId()
    {
        return R.string.zip_source_code;
    }

    @Override
    protected Param[] getParams()
    {
        return Params.ZIP_SOURCE_CODE_PARAMS;
    }

    @Override
    public void tool()
    {
        disable();
        notification = new NotificationProgress(context, R.string.zip_source_code);

        ZipParams params = new ZipParams();
        params.dir = new File(Params.SOURCE_APP_DIR.getValue());
        params.result = new File(Params.ZIP_RESULT_DIR.getValue(), params.dir.getName() + ".zip");
        params.result.delete();
        params.compression = 6;
        params.deleteSourceFiles = false;
        params.bufferSize = Constants.BUFFER_SIZE;
        params.addExclude("release/.*");
        params.addExclude("\\.git/.*");
        params.addExclude("app/build/bin/classesdebug/.*");
        params.addExclude("app/build/bin/classesrelease/.*");
        params.addExclude("app/build/bin/jardex/.*");
        params.addExclude("app/build/bin/res/.*");
        params.addExclude("app/build/bin/classes\\.dex");
        params.addExclude("app/build/bin/resources\\.ap_");

        new ZipTask(context, this).execute(params);
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
}
