package ru.net.serbis.tools.data.param;

import android.app.*;
import android.view.*;
import android.widget.*;
import java.text.*;
import java.util.*;
import ru.net.serbis.tools.listener.*;

public class EditDateParam extends DateTimeViewParam implements DatePickerDialog.OnDateSetListener
{
    private TextView textView;

    public EditDateParam(String paramName, Date defaultValue, boolean stored)
    {
        super(paramName, defaultValue, stored);
    }

    @Override
    protected SimpleDateFormat getFormat()
    {
        return new SimpleDateFormat("yyyy.MM.dd");
    }

    @Override
    public void initViewValue(View parent)
    {
        super.initViewValue(parent);

        textView = getViewValue(parent);
        textView.setOnClickListener(
            new ViewOnClickListener<TextView>()
            {
                @Override
                public void onClickView(TextView view)
                {
                    Date date = getValue(view);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    new DatePickerDialog(
                        context,
                        EditDateParam.this,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        );
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        setValue(textView, cal.getTime());
    }
}
