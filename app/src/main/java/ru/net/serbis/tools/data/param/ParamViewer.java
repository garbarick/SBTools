package ru.net.serbis.tools.data.param;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.param.*;

import ru.net.serbis.tools.R;

public class ParamViewer extends TextViewParam
{
    public ParamViewer(String name, String value)
    {
        super(name, value, false);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_viewer;
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
                    intent.putExtra(UtilsConstants.TITLE, getName());
                    intent.putExtra(Constants.VALUE, value);
                    context.startActivityForResult(intent, Constants.DELETE_PROPERTY);
                }
            }
        );
    }
}
