package ru.net.serbis.tools.adapter;

import android.content.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;

public class ResoursesXmlAdapter extends ResoursesAdapter
{
    public ResoursesXmlAdapter(Context context)
    {
        super(context, android.R.layout.class, ResType.LAYOUT, R.layout.resource_xml);
    }
}
