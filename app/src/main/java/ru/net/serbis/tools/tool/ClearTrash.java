package ru.net.serbis.tools.tool;

import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;

public class ClearTrash extends Tool implements TaskCallback<Integer>
{
    private boolean start = true;

    public ClearTrash()
    {
        super(
            R.layout.tool_clear_trash,
            R.id.clear_trash,
            R.id.clear_trash_set
        );
    }

    @Override
    protected void onClick(int id)
    {
        switch (id)
        {
            case R.id.clear_trash:
                clearTrash();
                break;
                
            case R.id.clear_trash_set:
                new ParamsFragment(context, R.string.clear_trash_set, Params.CLEAR_TRASH_PARAMS);
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.clear_trash;
    }

    private void clearTrash()
    {
        disable();
        notification = new NotificationProgress(context, R.string.clear_trash);
        new ClearTrashTask(context, this).execute();
    }

    @Override
    public void progress(int progress)
    {
        notification.setProgress(progress);
        bar.setProgress(progress);
    }

    @Override
    public void onResult(Integer result, TaskError error)
    {
        notification.cancel();
        enable();
        if (error != null)
        {
            UITool.get().toast(context, error);
        }
        else
        {
            String format = context.getResources().getString(R.string.files_deleted);
            String text = String.format(format, result);
            UITool.get().toast(context, text);
        }
    }

    @Override
    public void setMain(LinearLayout main)
    {
        super.setMain(main);
        if (start && Params.CLEAN_UP_ON_START.getValue(context))
        {
            clearTrash();
        }
        start = false;
    }
}
