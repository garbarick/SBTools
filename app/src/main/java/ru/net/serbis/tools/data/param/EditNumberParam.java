package ru.net.serbis.tools.data.param;

import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;

public class EditNumberParam extends NumberParam<EditText>
{
    public EditNumberParam(int nameId, Integer defaultValue)
    {
        super(nameId, defaultValue);
    }
    
    public EditNumberParam(String paramName, Integer defaultValue, boolean stored)
    {
        super(paramName, defaultValue, stored);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_number;
    }

    @Override
    public void initViewValue(View parent)
    {
        EditText view = getViewValue(parent);
        setValue(view, getValue());
    }

    @Override
    public void setValue(EditText view, Integer value)
    {
        view.setText(typeToString(value));
    }

    @Override
    public Integer getValue(EditText view)
    {
        return stringToType(view.getText().toString());
    }
}
