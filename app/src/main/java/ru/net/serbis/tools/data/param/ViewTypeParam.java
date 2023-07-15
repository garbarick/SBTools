package ru.net.serbis.tools.data.param;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;

public class ViewTypeParam extends SpinnerParam<ViewType>
{
    public ViewTypeParam()
    {
        super(R.string.type, ViewType.JAVA, ViewType.class.getEnumConstants());
    }

    @Override
    public String typeToString(ViewType value)
    {
        return value.name();
    }

    @Override
    public ViewType stringToType(String value)
    {
        return ViewType.valueOf(value);
    }
}
