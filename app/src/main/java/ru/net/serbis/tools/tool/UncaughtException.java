package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;

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
            context.getResources().getString(R.string.uncaught_exception)
        );
    }
}
