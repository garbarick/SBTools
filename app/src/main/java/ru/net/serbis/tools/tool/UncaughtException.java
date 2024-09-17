package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public class UncaughtException extends NoImageTool
{
    @Override
    public void tool()
    {
        throw new RuntimeException(
            Strings.get().get(R.string.uncaught_exception)
        );
    }

    @Override
    public int getNameId()
    {
        return R.string.uncaught_exception;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }
}
