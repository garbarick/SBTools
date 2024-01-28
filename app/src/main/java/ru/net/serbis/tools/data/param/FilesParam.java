package ru.net.serbis.tools.data.param;

import android.content.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.listener.*;
import ru.net.serbis.tools.util.*;

public class FilesParam extends Param<Set<String>, Button>
{
    private String paramName;
    private Set<String> files;

    public FilesParam(int nameId, String paramName)
    {
        super(nameId, new TreeSet<String>());
        this.paramName = paramName;
    }

    public FilesParam(int nameId)
    {
        this(nameId, null);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_files;
    }

    @Override
    public void initViewValue(View parent)
    {
        Button view = getViewValue(parent);
        setValue(view, getValue(context));
        view.setOnClickListener(
            new ViewOnClickListener<Button>()
            {
                @Override
                public void onClickView(final Button view)
                {
                    new FilesDialog(context, getValue(view))
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
    public Set<String> getValue(Context context)
    {
        return SysTool.get().getPreferences(context).getStringSet(paramName, new TreeSet<String>());
    }

    @Override
    public void saveValue(Set<String> value)
    {
        SharedPreferences.Editor editor = SysTool.get().getPreferencesEditor(context);
        editor.putStringSet(paramName, value);
        editor.commit();
    }

    @Override
    public String typeToString(Set<String> value)
    {
        return null;
    }

    @Override
    public Set<String> stringToType(String value)
    {
        return null;
    }

    @Override
    public void setValue(Button button, Set<String> files)
    {
        this.files = files;
        if (files.isEmpty())
        {
            button.setText(R.string.choose_files);
        }
        else
        {
            String format = context.getResources().getString(R.string.chosen_files);
            String text = String.format(format, files.size());
            button.setText(text);
        }
    }

    @Override
    public Set<String> getValue(Button view)
    {
        return files;
    }
}
