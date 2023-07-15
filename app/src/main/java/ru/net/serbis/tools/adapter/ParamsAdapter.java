package ru.net.serbis.tools.adapter;

import android.app.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.util.*;

public class ParamsAdapter
{
    private Activity context;
    private Map<Integer, Param> params = new HashMap<Integer, Param>();
    private Map<Integer, View> views = new HashMap<Integer, View>();

    public ParamsAdapter(Activity context, View parent, Param[] params)
    {
        this.context = context;
        int position = 0;
        for (Param param : params)
        {
            param.setContext(context);
            this.params.put(position, param);
            views.put(position, getView(param, parent, position));
            position ++;
        }
    }

    public View getView(Param param, View parent, int position)
    {
        LinearLayout layout = UITool.findView(parent, R.id.params);
        View view = LayoutInflater.from(context).inflate(param.getLayoutId(), layout, false);
        layout.addView(view);
        param.initNameView(view);
        param.initViewValue(view);
        return view;
    }

    public void saveValues()
    {
        for (Map.Entry<Integer, Param> entry : params.entrySet())
        {
            int position = entry.getKey();
            Param param = entry.getValue();
            View view = views.get(position);
            View viewValue = param.getViewValue(view);
            param.saveViewValue(viewValue);
        }
    }

    public void updateValue(Param param, Object value)
    {
        for (Map.Entry<Integer, Param> entry : params.entrySet())
        {
            if (entry.getValue() == param)
            {
                View view = views.get(entry.getKey());
                View viewValue = param.getViewValue(view);
                param.setValue(viewValue, value);
            }
        }
    }

    public void reset()
    {
        for (Map.Entry<Integer, Param> entry : params.entrySet())
        {
            int position = entry.getKey();
            Param param = entry.getValue();
            param.saveValue(null);
        }
    }
}
