package ru.net.serbis.tools.data.param;

import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.dialog.*;

public class FileParam extends TextViewParam
{
    private boolean onlyFolder;

    public FileParam(int nameId, String defaultValue, boolean onlyFolder)
    {
        super(nameId, defaultValue);
        this.onlyFolder = onlyFolder;
    }

    @Override
    public void initViewValue(View parent)
    {
        final TextView view = getViewValue(parent);
        setValue(view, getValue(context));
        view.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    new FileChooser(context, R.string.choose_dir, onlyFolder)
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
