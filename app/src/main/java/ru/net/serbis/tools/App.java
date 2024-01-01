package ru.net.serbis.tools;

import android.app.*;
import java.lang.reflect.*;
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

        initCompression();
        initParams();
        initStrings();
        shareConnection.bind();

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(getApplicationContext()));
    }

    private void initCompression()
    {
        for (Compression item : Compression.class.getEnumConstants())
        {
            item.initName(this);
        }
    }

    private void initParams()
    {
        for (Field field : Params.class.getFields())
        {
            if (Param.class.isAssignableFrom(field.getType()))
            {
                Param param = getValue(field);
                param.initName(this);
            }
            else if (field.getType().isArray())
            {
                Param[] params = getValue(field);
                Params.PARAMS.put(field.getName(), params);
            }
        }
    }

    private <T> T getValue(Field field)
    {
        try
        {
            return (T) field.get(null);
        }
        catch (Exception e)
        {
            Log.error(this, e);
            return null;
        }
    }

    private void initStrings()
    {
        for (Strings item : Strings.class.getEnumConstants())
        {
            item.initValue(this);
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
