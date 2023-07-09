package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.tool.*;

public class ParamsAdapter
{
    private Context context;
    private Param[] params;
    private Map<Integer, View> views = new HashMap<Integer, View>();

    public ParamsAdapter(Context context, View parent, Param[] params)
    {
        this.context = context;
        this.params = params;
        int position = 0;
        for (Param param : params)
        {
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
        param.initViewValue(view, context);
        return view;
    }
    
    public void saveValues()
    {
        int position = 0;
        for (Param param : params)
        {
            View view = views.get(position);
            param.saveValue(context, view);
            position ++;
        }
    }
}
