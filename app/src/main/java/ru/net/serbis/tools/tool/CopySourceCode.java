package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.bean.*;

public class CopySourceCode extends NoImageTool
{
    @Override
    public int getNameId()
    {
        return R.string.copy_source_code;
    }

    @Override
    protected Param[] getParams()
    {
        return Params.COPY_SOURCE_PARAMS;
    }

    public void searchPackage(String path)
    {
        disable();
        notification = new NotificationProgress(context, R.string.search_package);
        new SearchPackage(
            new TaskCallback<String>()
            {
                @Override
                public void progress(int progress)
                {
                    CopySourceCode.this.progress(progress);
                }

                @Override
                public void onResult(String result, TaskError error)
                {
                    CopySourceCode.this.onResult(error);
                    if (paramsDialog != null)
                    {
                        paramsDialog.updateValue(Params.SOURCE_PACKAGE, result);
                    }
                }
            }
        ).execute(path);
    }

    private void progress(int progress)
    {
        notification.setProgress(progress);
        bar.setProgress(progress);
    }

    public void onResult(TaskError error)
    {
        notification.cancel();
        enable();
        if (error != null)
        {
            UITool.get().toast(error);
        }
    }

    @Override
    public void tool()
    {
        disable();
        notification = new NotificationProgress(context, R.string.search_package);
        new CopySourceTask(
            new TaskCallback<Boolean>()
            {
                @Override
                public void progress(int progress)
                {
                    CopySourceCode.this.progress(progress);
                }

                @Override
                public void onResult(Boolean result, TaskError error)
                {
                    CopySourceCode.this.onResult(error);
                }
            }
        ).execute();
    }
}
