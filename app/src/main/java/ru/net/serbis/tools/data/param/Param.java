package ru.net.serbis.tools.data.param;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.util.*;

public abstract class Param<T, V extends View>
{
    protected String name;
    private int nameId;
    protected T defaultValue;
    protected Activity context;
    protected ParamsAdapter adapter;

    public Param(int nameId, T defaultValue)
    {
        this.nameId = nameId;
        this.defaultValue = defaultValue;
    }

    public abstract int getLayoutId();

    public void initName(Context context)
    {
        name = context.getResources().getString(nameId);
    }

    public void setAdapter(ParamsAdapter adapter)
    {
        this.adapter = adapter;
    }

    public void initNameView(View parent)
    {
        TextView view = UITool.get().findView(parent, R.id.name);
        view.setText(name);
    }

    public abstract void initViewValue(View parent);

    public V getViewValue(View parent)
    {
        return UITool.get().findView(parent, R.id.value);
    }

    public void saveValue(T value)
    {
        SharedPreferences.Editor editor = SysTool.get().getPreferencesEditor(context);
        String data = value == null ? null : typeToString(value);
        editor.putString(name, data);
        editor.commit();
    }

    public abstract String typeToString(T value);

    public T getValue(Context context)
    {
        return stringToType(SysTool.get().getPreferences(context).getString(name, typeToString(defaultValue)));
    }

    public abstract T stringToType(String value);

    public void saveViewValue(V view)
    {
        T value = getValue(view);
        saveValue(value);
    }

    public abstract void setValue(V view, T value);
    public abstract T getValue(V view);

    public void setContext(Activity context)
    {
        this.context = context;
    }
}
