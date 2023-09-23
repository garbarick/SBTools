package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public class RenameFiles extends Tool
{
    public RenameFiles()
    {
        super(
            R.layout.tool_rename_files,
            R.id.rename_files,
            R.id.rename_files_set
        );
    }

    @Override
    protected void onClick(int id)
    {
        switch (id)
        {
            case R.id.rename_files:
                UITool.get().notImplementedYet(context);
                break;

            case R.id.rename_files_set:
                UITool.get().notImplementedYet(context);
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.rename_files;
    }
}
