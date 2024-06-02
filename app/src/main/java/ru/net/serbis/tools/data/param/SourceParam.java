package ru.net.serbis.tools.data.param;

import android.widget.*;
import ru.net.serbis.tools.data.*;

public class SourceParam extends FileParam
{
    public SourceParam(int nameId)
    {
        super(nameId, null, false, false, Constants.JAVA_EXT);
    }

    @Override
    protected void onChoose(TextView view, String path)
    {
        super.onChoose(view, path);
        Tools.COPY_SOURCE_CODE.searchPackage(path);
    }
}
