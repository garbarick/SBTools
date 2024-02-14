package ru.net.serbis.tools.data.param;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;

public class PeriodParam extends SpinnerParam<Period>
{
    public PeriodParam(int nameId)
    {
        super(nameId, Period.DISABLED, Period.class.getEnumConstants());
    }

    @Override
    public String typeToString(Period value)
    {
        return value.name();
    }

    @Override
    public Period stringToType(String value)
    {
        return Period.valueOf(value);
    }
}
