package ru.net.serbis.tools.data;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.tool.*;

public interface Tools
{
    Tool ZIP_DIR = new ZipDir();
    Tool SYS_DRAWABLES = new SysDrawables();
    Tool SYS_STRINGS = new SysStrings();
    Tool CHECK_PERMISSIONS = new CheckPermissions();
    Tool UNCAUGHT_EXCEPTION = new UncaughtException();
    Tool MOVE_FILE_TO_SHARE = new MoveFileToShare();
    Tool SYS_XML = new SystemXml();
}
