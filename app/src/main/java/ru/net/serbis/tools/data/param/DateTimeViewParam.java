package ru.net.serbis.tools.data.param;

import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;

public class DateTimeViewParam extends DateTimeParam<TextView>
{
    public DateTimeViewParam(int nameId, Date defaultValue)
    {
        super(nameId, defaultValue);
    }

    public DateTimeViewParam(String paramName, Date defaultValue, boolean stored)
    {
        super(paramName, defaultValue, stored);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_text;
    }

    @Override
    public void initViewValue(View parent)
    {
        TextView view = getViewValue(parent);
        setValue(view, getValue());
    }

    @Override
    public void setValue(TextView view, Date value)
    {
        view.setText(typeToString(value));
    }

    @Override
    public Date getValue(TextView view)
    {
        return stringToType(view.getText().toString());
    }
}
