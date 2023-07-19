package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ResoursesImgAdapter extends ResoursesAdapter
{
    public ResoursesImgAdapter(Context context)
    {
        super(context, android.R.drawable.class, ResType.DRAWABLE, R.layout.resource_img);
    }

    @Override
    protected void initView(View view, Resource resource, int position)
    {
        super.initView(view, resource, position);
        ImageView img = UITool.findView(view, R.id.img);
        img.setImageResource(resource.getId());
    }
}
