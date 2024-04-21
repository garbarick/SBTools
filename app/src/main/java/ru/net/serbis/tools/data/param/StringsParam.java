package ru.net.serbis.tools.data.param;

import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public class StringsParam extends Param<Set<String>, Button>
{
    private Set<String> values;

    public StringsParam(int nameId)
    {
        super(nameId, new TreeSet<String>());
    }

    public StringsParam(int nameId, String paramName)
    {
        super(nameId, paramName, new TreeSet<String>());
    }

    @Override
    public int getLayoutId()
    {
        return 0;
    }

    @Override
    public void initViewValue(View parent)
    {
        Button view = getViewValue(parent);
        setValue(view, getValue());
    }

    @Override
    public Set<String> getValue()
    {
        if (stored)
        {
            return Preferences.get().getStringSet(paramName);
        }
        return defaultValue;
    }

    @Override
    public void saveValue(Set<String> value)
    {
        if (stored)
        {
            Preferences.get().setStringSet(paramName, value);
        }
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
    public void setValue(Button button, Set<String> values)
    {
        this.values = values;
    }

    @Override
    public Set<String> getValue(Button view)
    {
        return values;
    }
}
