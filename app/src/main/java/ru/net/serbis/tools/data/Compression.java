package ru.net.serbis.tools.data;

import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public enum Compression
{
    ZERO(R.string.level_0, 0),
    ONE(R.string.level_1, 1),
    TWO(R.string.level_2, 2),
    THREE(R.string.level_3, 3),
    FOUR(R.string.level_4, 4),
    FIVE(R.string.level_5, 5),
    SIX(R.string.level_6, 6),
    SEVEN(R.string.level_7, 7),
    EIGHT(R.string.level_8, 8),
    NINE(R.string.level_9, 9);

    private int nameId;
    private String name;
    private int level;

    private static final Map<String, Compression> VALUES = new HashMap<String, Compression>();

    private Compression(int nameId, int level)
    {
        this.nameId = nameId;
        this.level = level;
    }

    public int getLevel()
    {
        return level;
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

    public static Compression get(String name)
    {
        return VALUES.get(name);
    }

    public static void initNames()
    {
        for (Compression item : Compression.class.getEnumConstants())
        {
            item.initName();
        }
    }
}
