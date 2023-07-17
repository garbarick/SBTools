package ru.net.serbis.tools.tool;

import android.app.*;
import android.content.*;
import android.widget.*;
import java.io.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.extension.share.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;

public class MoveFileToShare extends Tool implements TaskCallback
{
    ParamsDialog dialog;

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
                dialog = new ParamsDialog(context, R.string.move_file_to_share_set, Params.MOVE_FILE_TO_SHARE_PARAMS);
                break;
        }
    }

    private void moveFileToShare()
    {
        disable();
        String filePath = Params.FILE.getValue(context);
        String shareDir = Params.SHARE_DIR.getValue(context);
        new ShareTools(context).uploadFile(filePath, shareDir, this);
    }
    
    @Override
    public void progress(int value)
    {
        ProgressBar bar = UITool.findView(context, R.id.progress);
        bar.setProgress(value);
    }

    @Override
    public void onResult(boolean result, TaskError error)
    {
        if (result)
        {
            String filePath = Params.FILE.getValue(context);
            new File(filePath).delete();
        }
        else
        {
            UITool.toast(context, error);
        }
        enable();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (Activity.RESULT_OK == resultCode)
        {
            ShareFolders folders = new ShareFolders();
            if (folders.isMineResult(requestCode, data))
            {
                String path = new ShareFolders().getPath(data);
                dialog.updateValue(Params.SHARE_DIR, path);
            }
        }
    }
}
