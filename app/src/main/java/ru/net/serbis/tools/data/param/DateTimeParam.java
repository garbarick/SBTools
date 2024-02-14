package ru.net.serbis.tools.data.param;

import android.app.*;
import android.content.*;
import android.text.*;
import android.view.*;
import java.text.*;
import java.util.*;

public class DateTimeParam extends TextViewParam
{
    public DateTimeParam(int nameId)
    {
        super(nameId, null);
    }

    @Override
    public void initViewValue(View parent)
    {
        super.initViewValue(parent);
    }

    public void updateValue(Activity context)
    {
        setContext(context);
        String value = getFormat().format(new Date());
        saveValue(value);
    }

    public Date getDateValue(Context context)
    {
        String value = getValue(context);
        if (TextUtils.isEmpty(value))
        {
            return null;
        }
        try
        {
            return getFormat().parse(value);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    private SimpleDateFormat getFormat()
    {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    }
}
