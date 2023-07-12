package ru.net.serbis.tools.data;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.tool.*;

public interface Tools
{
    Tool ZIP_DIR = new ZipDir();
    Tool SYS_DRAWABLES = new SysDrawablesTool();
    Tool CHECK_PERMISSIONS = new CheckPermissions();
    Tool UNCAUGHT_EXCEPTION = new UncaughtException();
}
