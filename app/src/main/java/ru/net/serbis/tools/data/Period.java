package ru.net.serbis.tools.data;

import java.util.*;
import java.util.concurrent.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public enum Period
{
    DISABLED(R.string.period_disabled, 0),
    EVERY_TIME(R.string.period_every_time, 0),
    ONCE_A_DAY(R.string.period_once_a_day, 1),
    ONCE_TWO_DAYS(R.string.period_once_two_days, 2),
    ONCE_A_WEEK(R.string.period_once_a_week, 7);

    private int nameId;
    private String name;
    private int days;

    private static final Map<String, Period> VALUES = new HashMap<String, Period>();

    private Period(int nameId, int days)
    {
        this.nameId = nameId;
        this.days = days;
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

    public static Period get(String name)
    {
        return VALUES.get(name);
    }

    public boolean checkDays(Date last)
    {
        if (last == null)
        {
            return true;
        }
        Date cur = new Date();
        long mills = cur.getTime() - last.getTime();
        long days = TimeUnit.DAYS.convert(mills, TimeUnit.MILLISECONDS);
        return days >= this.days;
    }

    public static void initNames()
    {
        for (Period item : Period.class.getEnumConstants())
        {
            item.initName();
        }
    }
}
