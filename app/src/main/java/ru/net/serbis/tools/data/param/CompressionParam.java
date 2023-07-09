package ru.net.serbis.tools.data.param;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;

public class CompressionParam extends SpinnerParam<Compression>
{
    public CompressionParam()
    {
        super(R.string.compression, Compression.ZERO, Compression.class.getEnumConstants());
    }

    @Override
    public String typeToString(Compression value)
    {
        return value.name();
    }

    @Override
    public Compression stringToType(String value)
    {
        return Compression.valueOf(value);
    }
}
