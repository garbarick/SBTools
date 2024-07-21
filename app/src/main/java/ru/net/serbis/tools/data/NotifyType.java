package ru.net.serbis.tools.data;

import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.utils.*;

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

    public void initName()
    {
        name = Strings.get().get(nameId);
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

    public static void initNames()
    {
        for (NotifyType item : NotifyType.class.getEnumConstants())
        {
            item.initName();
        }
    }
}
