package ru.net.serbis.tools.data.param;

import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.param.*;

public class UnitParam extends SpinnerParam<Unit>
{
    public UnitParam(String name, Unit value, boolean stored)
    {
        super(name, value, stored, Unit.class.getEnumConstants());
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
