package ru.net.serbis.tools.data;

import android.content.*;
import ru.net.serbis.tools.*;

public enum Strings
{
    COUNT(R.string.count);

    private int id;
    private String value;

    public Strings(int id)
    {
        this.id = id;
    }

    public void initValue(Context context)
    {
        value = context.getResources().getString(id);
    }

    public String getValue()
    {
        return value;
    }
}
