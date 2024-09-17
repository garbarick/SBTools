package ru.net.serbis.tools.data;

import android.graphics.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public interface Params
{
    FileParam DIRECTORY = new FileParam(R.string.dir, IOTool.get().getDownloadPath(), true, false);
    EditTextParam ZIP_NAME = new EditTextParam(R.string.zip_name, IOTool.get().getDownloadFile().getName() + ".zip");
    CompressionParam COMPRESSION = new CompressionParam();
    BooleanParam DELETE_SOURCE_FOLRS = new BooleanParam(R.string.delete_source_files, true);
    ViewTypeParam VIEW_TYPE = new ViewTypeParam();
    FileParam FILE = new FileParam(R.string.file, IOTool.get().getDownloadPath(IOTool.get().getDownloadFile().getName() + ".zip"), false, true);
    ShareDirParam SHARE_DIR = new ShareDirParam(R.string.share_dir, null);
    EditNumberParam BUFFER_SIZE = new EditNumberParam(R.string.buffer_size, Constants.BUFFER_SIZE);
    NotifyTypeParam NOTIFY_TYPE = new NotifyTypeParam();
    NotifyIconParam NOTIFY_ICON = new NotifyIconParam();
    PeriodParam AUTO_CLEAN_UP = new PeriodParam(R.string.auto_clean_up);
    FilesParam TRASH_FILES = new FilesParam(R.string.trash_files, "clearTrash", false, false);
    FilesParam DIRS_TO_FILES = new FilesParam(R.string.dirs_to_files, true, false);
    DateTimeViewParam LAST_CLEAN_UP = new DateTimeViewParam(R.string.last_clean_up, null);
    FileParam EXPORT_IMPORT_DIR = new FileParam(R.string.export_import_dir, IOTool.get().getExternalFile("backups"), true, false);
    StringsParam HIDE_TOOLS = new StringsParam(R.string.hide_tools);
    StringsParam MARKS = new StringsParam(R.string.marks);
    SourceParam SOURCE = new SourceParam(R.string.source);
    SourcePackageParam SOURCE_PACKAGE = new SourcePackageParam(R.string.source_package);
    FileParam TARGET = new FileParam(R.string.target, null, true, false);
    EditTextParam TARGET_PACKAGE = new EditTextParam(R.string.target_package, "com.test");
    FileParam SOURCE_APP_DIR = new FileParam(R.string.source_app_dir, IOTool.get().getExternalFile("AppProjects/SBTools"), true, false);
    FileParam ZIP_RESULT_DIR = new FileParam(R.string.zip_result_dir, IOTool.get().getDownloadPath(), true, false);
    BooleanParam RELEASE_APK = new BooleanParam(R.string.release_apk, true);
    BooleanParam RELEASE_JAR = new BooleanParam(R.string.release_jar, false);
    SeekBarParam BRIGHTNESS = new SeekBarParam(R.string.brightness, 60, 180, 120);
    ColorParam COLOR_FILTER = new ColorParam(R.string.color_filter, Color.GRAY);
    
    Param[] ZIP_DIR_PARAMS = new Param[]{
        DIRECTORY,
        ZIP_NAME,
        COMPRESSION,
        DELETE_SOURCE_FOLRS,
        BUFFER_SIZE
    };

    Param[] MOVE_FILE_TO_SHARE_PARAMS = new Param[]{
        FILE,
        SHARE_DIR,
        BUFFER_SIZE
    };

    Param[] SYS_RESOURSES = new Param[]{
        VIEW_TYPE
    };

    Param[] NOTIFICATION_PARAMS = new Param[]{
        NOTIFY_TYPE,
        NOTIFY_ICON
    };

    Param[] CLEAR_TRASH_PARAMS = new Param[]{
        AUTO_CLEAN_UP,
        TRASH_FILES,
        DIRS_TO_FILES,
        LAST_CLEAN_UP
    };

    Param[] EXPORT_IMPORT_PARAMS = new Param[]{
        EXPORT_IMPORT_DIR
    };

    Param[] COPY_SOURCE_PARAMS = new Param[]{
        SOURCE,
        SOURCE_PACKAGE,
        TARGET,
        TARGET_PACKAGE
    };

    Param[] ZIP_SOURCE_CODE_PARAMS = new Param[]{
        SOURCE_APP_DIR,
        ZIP_RESULT_DIR,
        RELEASE_APK,
        RELEASE_JAR
    };

    Param[] SCREEN_FILTER_PARAMS = new Param[]{
        BRIGHTNESS,
        COLOR_FILTER
    };
}
