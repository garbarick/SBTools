package ru.net.serbis.tools.data;

import android.os.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.param.*;

public interface Params
{
    FolderParam DIRECTORY = new FolderParam(R.string.dir, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
    EditTextParam ZIP_NAME = new EditTextParam(R.string.zip_name, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getName() + ".zip");
    CompressionParam COMPRESSION = new CompressionParam();
    BooleanParam DELETE_SOURCE_FOLRS = new BooleanParam(R.string.delete_source_files, true);

    Param[] ZIP_DIR_PARAMS = new Param[]{
        DIRECTORY,
        ZIP_NAME,
        COMPRESSION,
        DELETE_SOURCE_FOLRS
    };
}
