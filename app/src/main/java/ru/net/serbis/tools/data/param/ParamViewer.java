package ru.net.serbis.tools.data.param;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.data.*;

public class ParamViewer extends TextViewParam
{
    public ParamViewer(String paramName, String defaultValue)
    {
        super(paramName, defaultValue);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_viewer;
    }

    @Override
    public String getValue()
    {
        return defaultValue;
    }

    @Override
    public void saveValue(String value)
    {
    }

    @Override
    public void initViewValue(View parent)
    {
        super.initViewValue(parent);
        TextView view = getViewValue(parent);
        view.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(context, ViewParam.class);
                    intent.putExtra(Constants.TITLE, paramName);
                    intent.putExtra(Constants.VALUE, defaultValue);
                    context.startActivity(intent);
                }
            }
        );
    }
}
