package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.dialog.*;

public class MarksTool extends Tool
{
    @Override
    protected void tool()
    {
        new MarksDialog(context);
    }

    @Override
    public int getNameId()
    {
        return R.string.marks;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.tool_marks;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }
}
