package ru.net.serbis.tools.data.param;

import android.widget.*;
import ru.net.serbis.tools.*;

public abstract class TextViewParam extends TextParam<TextView>
{
    public TextViewParam(int nameId, String defaultValue)
    {
        super(nameId, defaultValue);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_text;
    }

    @Override
    public void setValue(TextView view, String value)
    {
        view.setText(value);
    }

    @Override
    public String getValue(TextView view)
    {
        return view.getText().toString();
    }
}
