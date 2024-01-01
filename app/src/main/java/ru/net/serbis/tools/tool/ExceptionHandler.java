package ru.net.serbis.tools.tool;

import android.content.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler
{
    private Context context;

    public ExceptionHandler(Context context)
    {
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable error) 
    {
        try
        {
            showReport(error);
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
    }

    private void showReport(Throwable error)
    {
        Intent intent = new Intent(context, ExceptionReport.class);
        intent.putExtra(Constants.THROWABLE, error);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        stop();
    }

    private void stop()
    {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}
