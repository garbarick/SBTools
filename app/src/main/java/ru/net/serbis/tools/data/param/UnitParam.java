package ru.net.serbis.tools.data.param;

import ru.net.serbis.tools.data.*;

public class UnitParam extends SpinnerParam<Unit>
{
    public UnitParam(String paramName, Unit defaultValue, boolean stored)
    {
        super(paramName, defaultValue, stored, Unit.class.getEnumConstants());
    }

    @Override
    public String typeToString(Unit value)
    {
        return value.name();
    }

    @Override
    public Unit stringToType(String value)
    {
        return Unit.valueOf(value);
    }
}
