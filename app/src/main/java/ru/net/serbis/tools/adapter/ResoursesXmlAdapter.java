package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;

public class ResoursesXmlAdapter extends ResoursesAdapter
{
    public ResoursesXmlAdapter(Context context)
    {
        super(context, android.R.layout.class, ResType.LAYOUT, R.layout.resource_xml);
    }

    @Override
    protected void initView(View view, Resource resource)
    {
        super.initView(view, resource);
    }
}
