package ru.net.serbis.tools.data.param;

import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.extension.share.*;
import ru.net.serbis.utils.listener.*;
import ru.net.serbis.utils.param.*;

public class ShareDirParam extends TextViewParam
{
    public ShareDirParam(int nameId, String value)
    {
        super(nameId, value);
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
