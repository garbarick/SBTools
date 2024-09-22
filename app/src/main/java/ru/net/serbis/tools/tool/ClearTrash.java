package ru.net.serbis.tools.tool;

import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.notification.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.bean.*;
import ru.net.serbis.utils.param.*;

import ru.net.serbis.tools.R;

public class ClearTrash extends Tool implements TaskCallback<Integer>
{
    private boolean start = true;

    @Override
    public void tool()
    {
        disable();
        notification = new NotificationProgress(context, R.string.clear_trash, getImageId());
        new ClearTrashTask(context, this).execute();
    }

    @Override
    protected Param[] getParams()
    {
        return Params.CLEAR_TRASH_PARAMS;
    }

    @Override
    public int getNameId()
    {
        return R.string.clear_trash;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.tool_clear_trash;
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
        UITool.get().toast(error);
        if (error == null)
        {
            String text = Strings.get().get(R.string.files_deleted, result);
            UITool.get().toast(text);
        }
        Params.LAST_CLEAN_UP.saveValue(new Date());
        context.closeTool();
    }

    @Override
    public void setMain(LinearLayout main)
    {
        super.setMain(main);
        if (start)
        {
            Date last = Params.LAST_CLEAN_UP.getValue();
            Period period = Params.AUTO_CLEAN_UP.getValue();
            switch(period)
            {
                case DISABLED:
                    break;
                case EVERY_TIME:
                    tool();
                    break;
                case ONCE_A_DAY:
                case ONCE_TWO_DAYS:
                case ONCE_A_WEEK:
                    if (period.checkDays(last))
                    {
                        tool();
                    }
                    break;
            }
        }
        start = false;
    }
}
