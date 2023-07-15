package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ResoursesStringAdapter extends ResoursesAdapter
{
    public ResoursesStringAdapter(Context context)
    {
        super(context, android.R.string.class, ResType.STRING, R.layout.resource_string);
    }

    @Override
    protected void initView(View view, Resource resource)
    {
        super.initView(view, resource);
        TextView value = UITool.findView(view, R.id.value);
        value.setText(resource.getId());
    }
}
