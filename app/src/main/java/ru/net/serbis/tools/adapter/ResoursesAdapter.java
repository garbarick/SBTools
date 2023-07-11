package ru.net.serbis.tools.adapter;

import android.content.*;
import android.content.res.*;
import android.view.*;
import android.widget.*;
import java.lang.reflect.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ResoursesAdapter extends ArrayAdapter<Resource>
{
    public ResoursesAdapter(Context context)
    {
        super(context, android.R.layout.simple_list_item_activated_1);
        init();
    }

    private void init()
    {
        Resources res = Resources.getSystem();
        for (Field field : android.R.drawable.class.getFields())
        {
            try
            {
                int id = res.getIdentifier(field.getName(), "drawable", "android");
                add(new Resource("android.R.drawable." + field.getName(), id));
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
           view = LayoutInflater.from(getContext()).inflate(R.layout.resource, parent, false);
       }
       Resource resource = getItem(position);
       ImageView img = UITool.findView(view, R.id.img);
       img.setImageResource(resource.getId());
       TextView name = UITool.findView(view, R.id.name);
       name.setText(resource.getName());
       return view;
    }
}
