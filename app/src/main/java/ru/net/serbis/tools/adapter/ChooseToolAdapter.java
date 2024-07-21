package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.utils.*;

public class ChooseToolAdapter extends ArrayAdapter<Tool>
{
    public ChooseToolAdapter(Context context)
    {
        super(context, android.R.layout.simple_list_item_activated_1);
        init();
    }

    private void init()
    {
        for (Tool tool : Tools.TOOLS_MAP.get().values())
        {
            if (tool.getImageId() == 0)
            {
                continue;
            }
            add(tool);
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        Tool tool = getItem(position);
        view = LayoutInflater.from(getContext()).inflate(R.layout.resource_img, parent, false);
        ImageView img = UITool.get().findView(view, R.id.img);
        img.setBackgroundResource(tool.getImageId());
        TextView name = UITool.get().findView(view, R.id.name);
        name.setText(tool.getNameId());
        return view;
    }
}
