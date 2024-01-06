package ru.net.serbis.tools.data;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.util.*;

public interface Params
{
    FileParam DIRECTORY = new FileParam(R.string.dir, IOTool.get().getDownloadPath(), true, false);
    EditTextParam ZIP_NAME = new EditTextParam(R.string.zip_name, IOTool.get().getDownloadFile().getName() + ".zip");
    CompressionParam COMPRESSION = new CompressionParam();
    BooleanParam DELETE_SOURCE_FOLRS = new BooleanParam(R.string.delete_source_files, true);
    ViewTypeParam VIEW_TYPE = new ViewTypeParam();
    FileParam FILE = new FileParam(R.string.file, IOTool.get().getDownloadPath(IOTool.get().getDownloadFile().getName() + ".zip"), false, true);
    ShareDirParam SHARE_DIR = new ShareDirParam(R.string.share_dir, null);
    NumberParam BUFFER_SIZE = new NumberParam(R.string.buffer_size, 10240);
    NotifyTypeParam NOTIFY_TYPE = new NotifyTypeParam();
    
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

    Param[] SYS_DRAWABLES_PARAMS = new Param[]{
        VIEW_TYPE
    };

    Param[] SYS_STRINGS_PARAMS = new Param[]{
        VIEW_TYPE
    };

    Param[] SYS_XML_PARAMS = new Param[]{
        VIEW_TYPE
    };

    Param[] SYS_COLORS_PARAMS = new Param[]{
        VIEW_TYPE
    };

    Param[] SYS_THEMES_PARAMS = new Param[]{
        VIEW_TYPE
    };

    Param[] NOTIFICATION_PARAMS = new Param[]{
        NOTIFY_TYPE
    };

    Holder<String, Param[]> PARAMS = new Holder<String, Param[]>();
}
