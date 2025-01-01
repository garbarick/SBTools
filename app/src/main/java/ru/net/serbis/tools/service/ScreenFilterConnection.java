package ru.net.serbis.tools.service;

import android.app.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.connection.*;

public class ScreenFilterConnection extends ExtConnection
{
    public ScreenFilterConnection(Application app)
    {
        super(app);
    }

    @Override
    protected String packageName()
    {
        return App.class.getPackage().getName();
    }

    @Override
    protected String serviceName()
    {
        return ScreenFilterService.class.getName();
    }
}
