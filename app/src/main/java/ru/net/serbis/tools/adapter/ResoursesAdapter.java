package ru.net.serbis.tools.adapter;

import android.content.*;
import android.graphics.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.resource.*;
import ru.net.serbis.tools.util.*;

public abstract class ResoursesAdapter extends ArrayAdapter<Resource>
{
    private int rowLayout;
    private int selected = -1;
    
    public ResoursesAdapter(Context context, ResType type, int rowLayout)
    {
        super(context, android.R.layout.simple_list_item_activated_1);
        this.rowLayout = rowLayout;
        init(type);
    }

    public void setSelection(int position)
    {
        selected = position;
    }

    public int getSelection()
    {
        return selected;
    }

    protected void init(ResType type)
    {
        for (Resource resource : ResourceLoader.get().get(type))
        {
            add(resource);
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
       view = LayoutInflater.from(getContext()).inflate(rowLayout, parent, false);
       Resource resource = getItem(position);
       initView(view, resource, position);
       return view;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent)
    {
        return getView(position, view, parent);
    }

    protected void initView(View view, Resource resource, int position)
    {
        TextView name = UITool.get().findView(view, R.id.name);
        name.setText(resource.getName(getContext()));
        if (selected == position)
        {
            name.setTextColor(Color.BLACK);
            view.setBackgroundColor(Color.CYAN);
        }
    }
}
