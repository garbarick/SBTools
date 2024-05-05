package ru.net.serbis.tools.data.mark;

import java.util.*;

public class MarkSorter implements Comparator<Mark>
{
    private long current = new Date().getTime();

    @Override
    public int compare(Mark p1, Mark p2)
    {
        int p = getWeight(p2).compareTo(getWeight(p1));
        if (p != 0)
        {
            return p;
        }
        return p1.getName().compareTo(p2.getName());
    }
    
    public Long getWeight(Mark mark)
    {
        return current - mark.getNextDate().getTime();
    }
}
