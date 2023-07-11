package ru.net.serbis.tools;

import android.app.*;
import java.lang.reflect.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.util.*;

public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        initCompression();
        initParams();
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
}
