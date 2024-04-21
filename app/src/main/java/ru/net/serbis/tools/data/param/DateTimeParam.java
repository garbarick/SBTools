package ru.net.serbis.tools.data.param;

import android.text.*;
import android.view.*;
import java.text.*;
import java.util.*;

public abstract class DateTimeParam<V extends View> extends Param<Date, V>
{
    public DateTimeParam(int nameId, Date defaultValue)
    {
        super(nameId, defaultValue);
    }

    public DateTimeParam(String paramName, Date defaultValue, boolean stored)
    {
        super(paramName, defaultValue, stored);
    }

    @Override
    public String typeToString(Date value)
    {
        if (value == null)
        {
            return null;
        }
        return getFormat().format(value);
    }

    @Override
    public Date stringToType(String value)
    {
        if (TextUtils.isEmpty(value))
        {
            return null;
        }
        try
        {
            return getFormat().parse(value);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    protected SimpleDateFormat getFormat()
    {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    }
}
