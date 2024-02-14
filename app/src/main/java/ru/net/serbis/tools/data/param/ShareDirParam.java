package ru.net.serbis.tools.data.param;

import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.extension.share.*;
import ru.net.serbis.tools.listener.*;

public class ShareDirParam extends TextViewParam
{
    public ShareDirParam(int nameId, String defaultValue)
    {
        super(nameId, defaultValue);
    }

    @Override
    public void initViewValue(View parent)
    {
        super.initViewValue(parent);

        TextView view = getViewValue(parent);
        view.setOnClickListener(
            new ViewOnClickListener<TextView>()
            {
                @Override
                public void onClickView(TextView view)
                {
                    ShareFolders folders = new ShareFolders();
                    context.startActivityForResult(
                        folders.getFoldersIntent(),
                        folders.getFolderResult());
                }
            }
        );
    }
}
