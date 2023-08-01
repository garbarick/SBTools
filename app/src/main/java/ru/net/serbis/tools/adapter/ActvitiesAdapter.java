package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.util.*;
import android.graphics.*;

public class ActvitiesAdapter extends ArrayAdapter<ActivityItem>
{
    private Collection<ActivityItem> items;
    private PackageItem current;
    private boolean enabled = true;

    public ActvitiesAdapter(Context context, Collection<ActivityItem> items)
    {
        super(context, android.R.layout.simple_list_item_activated_1);
        this.items = items;
        addAll(items);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        view = LayoutInflater.from(getContext()).inflate(R.layout.activity, parent, false);
        ActivityItem item = getItem(position);

        TextView label = UITool.get().findView(view, R.id.label);
        if (item == current)
        {
            label.setText("..");
        }
        else
        {
            label.setText(item.getLabel());
        }

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

    public void onItemClick(ActivitiesDialog dialog, ListView list, int position)
    {
        if (!enabled)
        {
            return;
        }
        ActivityItem item = getItem(position);
        if (item instanceof PackageItem)
        {
            if (item == current)
            {
                setNotifyOnChange(false);
                clear();
                current = null;
                addAll(items);
                selectItem(list, getPosition(item));
                setNotifyOnChange(true);
                notifyDataSetChanged();
                dialog.setDialogTitle(null);
            }
            else
            {
                current = (PackageItem)item;
                if (current.isReady())
                {
                    updateCurrent(dialog, list, current.getChildren());
                }
                else
                {
                    dialog.loadChildren(current);
                }
            }

        }
    }

    private void selectItem(ListView list, int position)
    {
        list.setItemChecked(position, true);
        list.smoothScrollToPosition(position);
    }

    public void updateCurrent(ActivitiesDialog dialog, ListView list, Collection<ActivityItem> children)
    {
        setNotifyOnChange(false);
        clear();
        add(current);
        addAll(children);
        selectItem(list, 0);
        setNotifyOnChange(true);
        notifyDataSetChanged();
        dialog.setDialogTitle(current.getLabel());
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

    @Override
    public boolean areAllItemsEnabled()
    {
        if (enabled)
        {
            return super.areAllItemsEnabled();
        }
        return false;
    }
    
    public boolean inLevelTwo()
    {
        return current != null;
    }
}
