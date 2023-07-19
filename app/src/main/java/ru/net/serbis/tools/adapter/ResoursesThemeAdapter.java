package ru.net.serbis.tools.adapter;

import android.content.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;

public class ResoursesThemeAdapter extends ResoursesAdapter
{
    public ResoursesThemeAdapter(Context context)
    {
        super(context, android.R.style.class, ResType.STYLE, R.layout.resource_xml);
    }

    @Override
    public void add(Resource resource)
    {
        if (resource.getName().startsWith("Theme"))
        {
            super.add(resource);
        }
    }
}
