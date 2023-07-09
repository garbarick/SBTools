package ru.net.serbis.tools.data;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.tool.*;

public interface Tools
{
    Tool ZIP_DIR = new Tool(R.layout.zip_dir, new Integer[]{R.id.zip_dir, R.id.zip_dir_set});
    Tool SYS_DRAWABLES = new Tool(R.layout.sys_drawables, new Integer[]{R.id.sys_drawables});
}
