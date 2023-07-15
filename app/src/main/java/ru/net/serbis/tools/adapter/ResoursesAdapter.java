package ru.net.serbis.tools.adapter;

import android.content.*;
import android.content.res.*;
import android.view.*;
import android.widget.*;
import java.lang.reflect.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public abstract class ResoursesAdapter extends ArrayAdapter<Resource>
{
    private Class sysClass;
    private ResType type;
    private int rowLayout;
    
    public ResoursesAdapter(Context context, Class sysClass, ResType type, int rowLayout)
    {
        super(context, android.R.layout.simple_list_item_activated_1);
        this.sysClass = sysClass;
        this.type = type;
        this.rowLayout = rowLayout;
        init();
    }

    private void init()
    {
        Resources res = Resources.getSystem();
        for (Field field : sysClass.getFields())
        {
            try
            {
                int id = res.getIdentifier(field.getName(), type.getValue(), "android");
                add(new Resource(field.getName(), id, type));
            }
            catch (Exception e)
            {
                Log.error(this, e);
            }
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
       if (view == null)
       {
           view = LayoutInflater.from(getContext()).inflate(rowLayout, parent, false);
       }
       Resource resource = getItem(position);
       initView(view, resource);
       return view;
    }

    protected void initView(View view, Resource resource)
    {
        TextView name = UITool.findView(view, R.id.name);
        name.setText(resource.getName(getContext()));
    }
}
