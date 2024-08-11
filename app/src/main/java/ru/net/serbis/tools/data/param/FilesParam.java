package ru.net.serbis.tools.data.param;

import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.listener.*;
import ru.net.serbis.utils.*;

public class FilesParam extends StringsParam
{
    private boolean onlyFolder;
    private boolean onlyFile;

    public FilesParam(int nameId, String paramName, boolean onlyFolder, boolean onlyFile)
    {
        super(nameId, paramName);
        this.onlyFolder = onlyFolder;
        this.onlyFile = onlyFile;
    }

    public FilesParam(int nameId, boolean onlyFolder, boolean onlyFile)
    {
        this(nameId, null, onlyFolder, onlyFile);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_files;
    }

    @Override
    public void initViewValue(View parent)
    {
        super.initViewValue(parent);
        getViewValue(parent).setOnClickListener(
            new ViewOnClickListener<Button>()
            {
                @Override
                public void onClickView(final Button view)
                {
                    new FilesDialog(context, nameId, getValue(view), onlyFolder, onlyFile)
                    {
                        @Override
                        protected void onResult(Set<String> result)
                        {
                            setValue(view, result);
                        }
                    }.show();
                }
            }
        );
    }

    @Override
    public void setValue(Button button, Set<String> values)
    {
        super.setValue(button, values);
        if (values.isEmpty())
        {
            button.setText(R.string.choose_files);
        }
        else
        {
            String text = Strings.get().get(R.string.chosen_files, values.size());
            button.setText(text);
        }
    }
}
