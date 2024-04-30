package ru.net.serbis.tools.tool;

import android.app.*;
import android.content.*;
import java.io.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.extension.share.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;

public class MoveFileToShare extends Tool implements TaskCallback<Boolean>
{
    @Override
    public void tool()
    {
        UITool.get().setProgress(true);
        disable();
        notification = new NotificationProgress(context, R.string.move_file_to_share);
        String filePath = Params.FILE.getValue();
        String shareDir = Params.SHARE_DIR.getValue();
        Integer bufferSize = Params.BUFFER_SIZE.getValue();
        new ShareTools(context).uploadFile(this, filePath, shareDir, bufferSize);
    }

    @Override
    protected Param[] getParams()
    {
        return Params.MOVE_FILE_TO_SHARE_PARAMS;
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
        if (result)
        {
            String filePath = Params.FILE.getValue();
            new File(filePath).delete();
        }
        else
        {
            UITool.get().toast(error);
        }
        notification.cancel();
        enable();
        UITool.get().setProgress(false);
        context.closeTool();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (paramsDialog != null && Activity.RESULT_OK == resultCode)
        {
            ShareFolders folders = new ShareFolders();
            if (folders.isMineResult(requestCode, data))
            {
                String path = new ShareFolders().getPath(data);
                paramsDialog.updateValue(Params.SHARE_DIR, path);
            }
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.move_file_to_share;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.tool_move_file_to_share;
    }
}
