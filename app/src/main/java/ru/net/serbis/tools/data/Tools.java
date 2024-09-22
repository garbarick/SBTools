package ru.net.serbis.tools.data;

import ru.net.serbis.tools.tool.*;
import ru.net.serbis.utils.bean.*;

public interface Tools
{
    Tool ZIP_DIR = new ZipDir();
    Tool SYS_DRAWABLES = new SysDrawables();
    Tool SYS_STRINGS = new SysStrings();
    Tool CHECK_PERMISSIONS = new CheckPermissions();
    Tool UNCAUGHT_EXCEPTION = new UncaughtException();
    Tool MOVE_FILE_TO_SHARE = new MoveFileToShare();
    Tool SYS_LAYOUTS = new SysLayouts();
    Tool SYS_COLORS = new SysColors();
    Tool SYS_RESOURCES = new SysResources();
    Tool SYS_THEMES = new SysThemes();
    Tool PROGRESS_IN_NOTIFICATION = new ProgressInNotification();
    Tool ACTIVITIES = new Activities();
    Tool CLEAR_TRASH = new ClearTrash();
    Tool HIDE_TOOLS = new HideTools();
    Tool EXPORT_IMPORT = new ExportImport();
    Tool MARKS_TOOL = new MarksTool();
    Tool PREFERENCES_TOOL = new PreferencesTool();
    CopySourceCode COPY_SOURCE_CODE = new CopySourceCode();
    Tool ZIP_SOURCE_CODE = new ZipSourceCode();;
    Tool SCREEN_FILTER = new ScreenFilter();
    Tool SWITCH_LAUNCHER = new SwitchLauncher();

    Tool[] MAIN_TOOLS = new Tool[]{
        ZIP_DIR,
        MOVE_FILE_TO_SHARE,
        CHECK_PERMISSIONS,
        UNCAUGHT_EXCEPTION,
        SYS_RESOURCES,
        PROGRESS_IN_NOTIFICATION,
        ACTIVITIES,
        CLEAR_TRASH,
        HIDE_TOOLS,
        EXPORT_IMPORT,
        MARKS_TOOL,
        PREFERENCES_TOOL,
        COPY_SOURCE_CODE,
        ZIP_SOURCE_CODE,
        SCREEN_FILTER,
        SWITCH_LAUNCHER
    };
    
    Tool[] SYS_RESOURCES_TOOLS = new Tool[]{
        SYS_DRAWABLES,
        SYS_STRINGS,
        SYS_LAYOUTS,
        SYS_COLORS,
        SYS_THEMES
    };

    Holder<String, Tool> TOOLS_MAP = new Holder<String, Tool>();
}
