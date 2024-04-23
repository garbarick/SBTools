package ru.net.serbis.tools.data;

import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public enum Unit
{
    DAYS(R.string.days, Calendar.DAY_OF_YEAR),
    WEEKS(R.string.weeks, Calendar.WEEK_OF_YEAR),
    MONTHS(R.string.months, Calendar.MONTH),
    YEARS(R.string.years, Calendar.YEAR);

    private int nameId;
    private String name;
    private int delta;

    private static final Map<String, Unit> VALUES = new HashMap<String, Unit>();

    private Unit(int nameId, int delta)
    {
        this.nameId = nameId;
        this.delta = delta;
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

    public static Unit get(String name)
    {
        return VALUES.get(name);
    }

    public static void initNames()
    {
        for (Unit item : Unit.class.getEnumConstants())
        {
            item.initName();
        }
    }

    public Date getNext(Date date, int period)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(delta, period);
        return calendar.getTime();
    }
}
