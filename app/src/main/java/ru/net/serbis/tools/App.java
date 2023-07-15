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

    @Override
    public void onCreate()
    {
        super.onCreate();

        initCompression();
        initParams();
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
                try
                {
                    Param param = (Param) field.get(null);
                    param.initName(this);
                }
                catch (Exception e)
                {
                    Log.error(this, e);
                }
            }
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
}
