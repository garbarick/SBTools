package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public class LocalResoucesAdapter extends ResoursesAdapter
{
    public LocalResoucesAdapter(Context context)
    {
        super(context, ResType.LOCAL_DRAWABLE, R.layout.resource_img);
    }

    @Override
    protected void initView(View view, Resource resource, int position)
    {
        TextView name = UITool.get().findView(view, R.id.name);
        name.setText(resource.getName());
        ImageView img = UITool.get().findView(view, R.id.img);
        img.setImageResource(resource.getId());
    }
}
