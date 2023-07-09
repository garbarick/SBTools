package ru.net.serbis.tools.data.param;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;

public abstract class SpinnerParam<T> extends Param<T, Spinner>
{
    protected T[] values;

    public SpinnerParam(int nameId, T defaultValue, T[] values)
    {
        super(nameId, defaultValue);
        this.values = values;
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_spinner;
    }

    @Override
    public void initViewValue(View parent, Context context)
    {
        Spinner view = getViewValue(parent);
        ArrayAdapter<T> adapter = new ArrayAdapter<T>(view.getContext(), android.R.layout.simple_spinner_item, this.values);  
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        view.setAdapter(adapter);
        view.setSelection(adapter.getPosition(getValue(context)));
    }

    @Override
    protected T getValue(Spinner view)
    {
        return (T) view.getSelectedItem();
    }
}
