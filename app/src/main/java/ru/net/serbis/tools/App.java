package ru.net.serbis.tools;

import android.app.*;
import android.content.*;
import java.util.*;
import ru.net.serbis.tools.connection.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.extension.share.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.tools.util.*;

public class App extends Application
{
    private ExtConnection shareConnection = new ShareConnection(this);
    private boolean progress;

    @Override
    public void onCreate()
    {
        super.onCreate();

        Context context = getApplicationContext();
        SysTool.get().set(context);
        UITool.get().set(context);

        initEnums();
        initParams();
        shareConnection.bind();

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(getApplicationContext()));
    }

    private void initEnums()
    {
        for (Compression item : Compression.class.getEnumConstants())
        {
            item.initName(this);
        }
        for (Strings item : Strings.class.getEnumConstants())
        {
            item.initValue(this);
        }
        for (NotifyType item : NotifyType.class.getEnumConstants())
        {
            item.initName(this);
        }
        for (Period item : Period.class.getEnumConstants())
        {
            item.initName(this);
        }
    }

    private void initParams()
    {
        for (Param param : Reflection.get().getValues(Params.class, Param.class).values())
        {
            param.initName(this);
        }
        for (Map.Entry<String, Param[]> entry : Reflection.get().getArrayValues(Params.class, Param.class).entrySet())
        {
            String name = entry.getKey();
            Param[] params = entry.getValue();
            Params.PARAMS.put(name, params);
        }
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        shareConnection.unBind();
    }

    public ExtConnection getShareConnection()
    {
        shareConnection.bind();
        return shareConnection;
	}

    public void setProgress(boolean progress)
    {
        this.progress = progress;
    }

    public boolean isProgress()
    {
        return progress;
    }
}
