package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public class UncaughtException extends Tool
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
