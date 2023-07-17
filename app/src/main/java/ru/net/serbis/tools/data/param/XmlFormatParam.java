package ru.net.serbis.tools.data.param;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;

public class XmlFormatParam extends SpinnerParam<XmlFormat>
{
    public XmlFormatParam()
    {
        super(R.string.formatter, XmlFormat.CUSTOM, XmlFormat.class.getEnumConstants());
    }

    @Override
    public String typeToString(XmlFormat value)
    {
        return value.name();
    }

    @Override
    public XmlFormat stringToType(String value)
    {
        return XmlFormat.valueOf(value);
    }
}
