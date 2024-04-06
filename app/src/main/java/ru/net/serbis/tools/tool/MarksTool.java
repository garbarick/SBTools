package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public class MarksTool extends Tool
{
    public MarksTool()
    {
        super(
            R.layout.tool_marks,
            R.id.marks,
            R.id.marks_set);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.marks:
                UITool.get().notImplementedYet();
                break;

            case R.id.marks_set:
                UITool.get().notImplementedYet();
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.marks;
    }
}
