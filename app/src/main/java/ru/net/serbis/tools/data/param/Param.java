package ru.net.serbis.tools.data.param;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.tool.*;

public abstract class Param<T, V extends View>
{
    protected String name;
    private int nameId;
    protected T defaultValue;

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

    public void initNameView(View parent)
    {
        TextView view = UITool.findView(parent, R.id.name);
        view.setText(name);
    }

    public abstract void initViewValue(View parent, Context context);

    protected V getViewValue(View parent)
    {
        return UITool.findView(parent, R.id.value);
    }

    protected SharedPreferences getPreferences(Context context)
    {
        return context.getSharedPreferences(Constants.APP, Context.MODE_PRIVATE);
    }

    protected SharedPreferences.Editor getPreferencesEditor(Context context)
    {
        return getPreferences(context).edit();
    }

    public void saveValue(Context context, T value)
    {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putString(name, typeToString(value));
        editor.commit();
    }

    public abstract String typeToString(T value);

    public T getValue(Context context)
    {
        return stringToType(getPreferences(context).getString(name, typeToString(defaultValue)));
    }

    public abstract T stringToType(String value);

    public void saveValue(Context context, View parent)
    {
        V view = getViewValue(parent);
        T value = getValue(view);
        saveValue(context, value);
    }

    protected abstract T getValue(V view);
}
