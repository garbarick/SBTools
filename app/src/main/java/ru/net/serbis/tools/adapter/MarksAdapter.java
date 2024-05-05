package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.mark.*;
import ru.net.serbis.tools.util.*;
import android.graphics.*;
import java.util.*;
import ru.net.serbis.tools.data.*;

public class MarksAdapter extends ArrayAdapter<Mark>
{
    public MarksAdapter(Context context)
    {
        super(context, android.R.layout.simple_list_item_activated_1);
        load();
        sort();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        view = getView(position, parent);
        Mark mark = getItem(position);
        ListView list = (ListView) parent;
        if (!list.isItemChecked(position))
        {
            if (mark.isExpired())
            {
                view.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_red_dark));
            }
            else if (mark.isWarning())
            {
                view.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_orange_dark));
            }
        }

        TextView name = UITool.get().findView(view, R.id.name);
        name.setText(mark.getName());

        TextView lastDate = UITool.get().findView(view, R.id.last_date);
        lastDate.setText(mark.getLastDateString());

        TextView nextDate = UITool.get().findView(view, R.id.next_date);
        nextDate.setText(mark.getNextDateString());

        TextView lastPeriod = UITool.get().findView(view, R.id.last_period);
        lastPeriod.setText("" + mark.getLastPeriod());

        TextView period = UITool.get().findView(view, R.id.period);
        period.setText("" + mark.getCurrentPeriod());

        return view;
    }

    private View getView(final int position, ViewGroup parent)
    {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.mark, parent, false);
        view.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    clickItem(view, position);
                }
            });
        ImageButton update = UITool.get().findView(view, R.id.update);
        update.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    clickItem(view, position);
                }
            });
        return view;
    }

    private void clickItem(View view, int position)
    {
        switch(view.getId())
        {
            case R.id.mark:
                ListView list = (ListView) view.getParent();
                list.setItemChecked(position, !list.isItemChecked(position));
                break;
            case R.id.update:
                update(position);
                break;
        }
        
    }

    private void update(int position)
    {
        Mark mark = getItem(position);
        mark.update();
        save();
        sort();
        notifyDataSetChanged();
    }

    public void sort()
    {
        sort(new MarkSorter());
    }

    private void load()
    {
        for (String data : Params.MARKS.getValue())
        {
            Mark mark = new Mark();
            mark.parse(JsonTool.get().parse(data));
            add(mark);
        }
    }

    public void save()
    {
        Set<String> data = new HashSet<String>();
        for (int i = 0; i < getCount(); i++)
        {
            Mark mark = getItem(i);
            data.add(mark.toJson().toString());
        }
        Params.MARKS.saveValue(data);
    }
}
