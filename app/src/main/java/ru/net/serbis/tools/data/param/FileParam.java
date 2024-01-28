package ru.net.serbis.tools.data.param;

import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.listener.*;

public class FileParam extends TextViewParam
{
    private boolean onlyFolder;
    private boolean onlyFile;

    public FileParam(int nameId, String defaultValue, boolean onlyFolder, boolean onlyFile)
    {
        super(nameId, defaultValue);
        this.onlyFolder = onlyFolder;
        this.onlyFile = onlyFile;
    }

    @Override
    public void initViewValue(View parent)
    {
        TextView view = getViewValue(parent);
        setValue(view, getValue(context));
        view.setOnClickListener(
            new ViewOnClickListener<TextView>()
            {
                @Override
                public void onClickView(final TextView view)
                {
                    new FileChooser(context, R.string.choose_dir, onlyFolder, onlyFile)
                    {
                        @Override
                        public void onChoose(String path)
                        {
                            view.setText(path);
                        }
                    };
                }
            }
        );
    }
}
