package ru.net.serbis.tools.data.param;

import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;

public class EditTextParam extends TextParam<EditText>
{
    public EditTextParam(int nameId, String defaultValue)
    {
        super(nameId, defaultValue);
    }

    public EditTextParam(String paramName, String defaultValue, boolean stored)
    {
        super(paramName, defaultValue, stored);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_edit;
    }

    @Override
    public void initViewValue(View parent)
    {
        EditText view = getViewValue(parent);
        setValue(view, getValue());
    }

    @Override
    public void setValue(EditText view, String value)
    {
        view.setText(value);
    }

    @Override
    public String getValue(EditText view)
    {
        return view.getText().toString();
    }
}
