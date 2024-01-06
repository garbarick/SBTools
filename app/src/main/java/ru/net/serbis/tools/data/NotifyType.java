package ru.net.serbis.tools.data;

import android.content.*;
import java.util.*;
import ru.net.serbis.tools.*;

public enum NotifyType
{
    STANDARD(R.string.standard),
    CUSTOM(R.string.custom);

    private int nameId;
    private String name;

    private static final Map<String, NotifyType> VALUES = new HashMap<String, NotifyType>();

    public NotifyType(int nameId)
    {
        this.nameId = nameId;
    }

    public void initName(Context context)
    {
        name = context.getResources().getString(nameId);
        VALUES.put(name, this);
    }

    @Override
    public String toString()
    {
        return name;
    }

    public static NotifyType get(String name)
    {
        return VALUES.get(name);
    }
}
