package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public class ActvitiesAdapter extends ArrayAdapter<ActivityItem>
{
    private boolean enabled = true;

    public ActvitiesAdapter(Context context)
    {
        super(context, android.R.layout.simple_list_item_activated_1);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        view = LayoutInflater.from(getContext()).inflate(R.layout.activity, parent, false);
        ActivityItem item = getItem(position);

        TextView label = UITool.get().findView(view, R.id.label);
        label.setText(item.getLabel());

        TextView name = UITool.get().findView(view, R.id.name);
        name.setText(item.getName());

        TextView packageName = UITool.get().findView(view, R.id.package_name);
        packageName.setText(item.getPackageName());

        ImageView img = UITool.get().findView(view, R.id.img);
        img.setImageDrawable(item.getDrawable());
        
        label.setEnabled(enabled);
        name.setEnabled(enabled);
        packageName.setEnabled(enabled);
        img.setEnabled(enabled);

        return view;
    }

    public void selectItem(ListView list, int position)
    {
        list.setItemChecked(position, true);
        list.smoothScrollToPosition(position);
    }

    public void initItems(Collection<ActivityItem> items)
    {
        setNotifyOnChange(false);
        clear();
        addAll(items);
        setNotifyOnChange(true);
        notifyDataSetChanged();
    }

    public void enable(boolean enabled)
    {
        this.enabled = enabled;
        notifyDataSetChanged();
    }

    @Override
    public boolean isEnabled(int position)
    {
        if (enabled)
        {
            return super.isEnabled(position);
        }
        return false;
    }
}
