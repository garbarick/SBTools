package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.dialog.*;

public class MarksTool extends Tool
{
    public MarksTool()
    {
        super(
            R.layout.tool_marks,
            R.id.marks);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.marks:
                new MarksDialog(context);
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.marks;
    }
}
