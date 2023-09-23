package ru.net.serbis.tools.tool;

import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;

public class ClearTrash extends Tool implements TaskCallback<Boolean>
{
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
                new FilesFragment(context);
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
        Set<String> pathes = SysTool.get().getPreferences(context).getStringSet(FilesDialog.KEY, new TreeSet<String>());
        new ClearTrashTask(this).execute(pathes.toArray(new String[0]));
    }

    @Override
    public void progress(int progress)
    {
        notification.setProgress(progress);
        bar.setProgress(progress);
    }

    @Override
    public void onResult(Boolean result, TaskError error)
    {
        notification.cancel();
        enable();
        if (!result)
        {
            UITool.get().toast(context, error);
        }
    }
}
