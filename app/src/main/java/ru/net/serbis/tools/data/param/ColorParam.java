package ru.net.serbis.tools.data.param;

import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.listener.*;

public class ColorParam extends NumberParam<Button>
{
    public ColorParam(int nameId, Integer value)
    {
        super(nameId, value);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_button;
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
                    new ColorPicker(context, getValue(view), false)
                    {
                        @Override
                        public void updateValue(Integer value)
                        {
                            setValue(view, value);
                        }
                    };
                }
            }
        );
    }

    @Override
    public void setValue(Button view, Integer value)
    {
        view.setBackgroundColor(value);
    }

    @Override
    public Integer getValue(Button view)
    {
        ColorDrawable color = (ColorDrawable) view.getBackground();
        return color.getColor();
    }
}
