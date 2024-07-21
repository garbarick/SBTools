package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public class ResoursesColorAdapter extends ResoursesAdapter
{
    public ResoursesColorAdapter(Context context)
    {
        super(context, ResType.COLOR, R.layout.resource_img);
    }

    @Override
    protected void initView(View view, Resource resource, int position)
    {
        super.initView(view, resource, position);
        ImageView img = UITool.get().findView(view, R.id.img);
        int color = getContext().getResources().getColor(resource.getId());
        img.setBackgroundColor(color);
    }
}
