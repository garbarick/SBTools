package ru.net.serbis.tools.data;

import ru.net.serbis.tools.tool.*;

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
    Tool COPY_SOURCE = new CopySource();

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
        COPY_SOURCE
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
