package ru.net.serbis.tools.data.param;

import android.view.*;
import ru.net.serbis.utils.*;

public abstract class NumberParam<V extends View> extends Param<Integer, V>
{
    public NumberParam(int nameId, Integer defaultValue)
    {
        super(nameId, defaultValue);
    }

    public NumberParam(String paramName, Integer defaultValue, boolean stored)
    {
        super(paramName, defaultValue, stored);
    }

    @Override
    public String typeToString(Integer value)
    {
        return value.toString();
    }

    @Override
    public Integer stringToType(String value)
    {
        try
        {
            return Integer.valueOf(value);
        }
        catch (Exception e)
        {
            Log.error(this, e);
            return defaultValue;
        }
    }
}
