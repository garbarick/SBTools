package ru.net.serbis.tools.tool;

import android.app.*;
import android.content.*;
import java.io.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.extension.share.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;

public class MoveFileToShare extends Tool implements TaskCallback<Boolean>
{
    private ParamsFragment dialog;

    public MoveFileToShare()
    {
        super(
            R.layout.tool_move_file_to_share,
            R.id.move_file_to_share,
            R.id.move_file_to_share_set);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.move_file_to_share:
                moveFileToShare();
                break;

            case R.id.move_file_to_share_set:
                dialog = new ParamsFragment(context, R.string.move_file_to_share_set, Params.MOVE_FILE_TO_SHARE_PARAMS);
                break;
        }
    }

    private void moveFileToShare()
    {
        UITool.get().setProgress(context, true);
        disable();
        notification = new NotificationProgress(context, R.string.move_file_to_share);
        String filePath = Params.FILE.getValue(context);
        String shareDir = Params.SHARE_DIR.getValue(context);
        Integer bufferSize = Params.BUFFER_SIZE.getValue(context);
        new ShareTools(context).uploadFile(this, filePath, shareDir, bufferSize);
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
            String filePath = Params.FILE.getValue(context);
            new File(filePath).delete();
        }
        else
        {
            UITool.get().toast(context, error);
        }
        notification.cancel();
        enable();
        UITool.get().setProgress(context, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (dialog != null && Activity.RESULT_OK == resultCode)
        {
            ShareFolders folders = new ShareFolders();
            if (folders.isMineResult(requestCode, data))
            {
                String path = new ShareFolders().getPath(data);
                dialog.updateValue(Params.SHARE_DIR, path);
            }
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.move_file_to_share;
    }
}
