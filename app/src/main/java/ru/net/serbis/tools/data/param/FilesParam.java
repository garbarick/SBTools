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
    private Set<String> files;
    private boolean onlyFolder;
    private boolean onlyFile;

    public FilesParam(int nameId, String paramName, boolean onlyFolder, boolean onlyFile)
    {
        super(nameId, new TreeSet<String>());
        this.paramName = paramName;
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
        Button view = getViewValue(parent);
        setValue(view, getValue());
        view.setOnClickListener(
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
    public Set<String> getValue()
    {
        return new TreeSet<String>(SysTool.get().getPreferences().getStringSet(paramName, new TreeSet<String>()));
    }

    @Override
    public void saveValue(Set<String> value)
    {
        SharedPreferences.Editor editor = SysTool.get().getPreferencesEditor();
        editor.putStringSet(paramName, value);
        editor.commit();
    }

    @Override
    public String typeToString(Set<String> value)
    {
        return JsonTool.get().toJsonString(value);
    }

    @Override
    public Set<String> stringToType(String value)
    {
        return JsonTool.get().toSet(value);
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
