package ru.net.serbis.tools.data.param;

import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;

public class SeekBarParam extends NumberParam<SeekBar>
{
    private int min;
    private int max;

    public SeekBarParam(int nameId, int min, int max, int defaultValue)
    {
        super(nameId, defaultValue);
        this.min = min;
        this.max = max;
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_seekbar;
    }

    @Override
    public void initViewValue(View parent)
    {
        SeekBar view = getViewValue(parent);
        view.setMin(min);
        view.setMax(max);
        setValue(view, getValue());
    }

    @Override
    public void setValue(SeekBar view, Integer value)
    {
        view.setProgress(value);
    }

    @Override
    public Integer getValue(SeekBar view)
    {
        return view.getProgress();
    }
}
