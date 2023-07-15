package ru.net.serbis.tools.data.param;

import android.widget.*;
import ru.net.serbis.tools.*;
import android.content.*;
import android.view.*;

public class BooleanParam extends Param<Boolean, CheckBox>
{
    public BooleanParam(int nameId, boolean defaultValue)
    {
        super(nameId, defaultValue);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_boolean;
    }

    @Override
    public void initViewValue(View parent)
    {
        CheckBox view = getViewValue(parent);
        setValue(view, getValue(context));
    }

    @Override
    public String typeToString(Boolean value)
    {
        return value.toString();
    }

    @Override
    public Boolean stringToType(String value)
    {
        return Boolean.parseBoolean(value);
    }

    @Override
    public void setValue(CheckBox view, Boolean value)
    {
        view.setChecked(value);
    }

    @Override
    public Boolean getValue(CheckBox view)
    {
        return view.isChecked();
    }
}
