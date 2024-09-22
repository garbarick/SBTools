package ru.net.serbis.tools.data.param;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.param.*;

public class NotifyTypeParam extends SpinnerParam<NotifyType>
{
    public NotifyTypeParam()
    {
        super(R.string.notify_type, NotifyType.STANDARD, NotifyType.class.getEnumConstants());
    }

    @Override
    public String typeToString(NotifyType value)
    {
        return value.name();
    }

    @Override
    public NotifyType stringToType(String value)
    {
        return NotifyType.valueOf(value);
    }
}
