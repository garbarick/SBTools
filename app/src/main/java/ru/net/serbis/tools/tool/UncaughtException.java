package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public class UncaughtException extends Tool
{
    public UncaughtException()
    {
        super(R.layout.tool_uncaught_exception, R.id.uncaught_exception);
    }

    @Override
    protected void onClick(int id)
    {
        switch (id)
        {
            case R.id.uncaught_exception:
                throw getError();
        }
    }

    private RuntimeException getError()
    {
        return new RuntimeException(
            Strings.get().get(R.string.uncaught_exception)
        );
    }

    @Override
    public int getNameId()
    {
        return R.string.uncaught_exception;
    }
}
