package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.data.*;

public class CopySource extends NoImageTool
{
    @Override
    public int getNameId()
    {
        return R.string.copy_source;
    }

    @Override
    protected Param[] getParams()
    {
        return Params.COPY_SOURCE_PARAMS;
    }
}
