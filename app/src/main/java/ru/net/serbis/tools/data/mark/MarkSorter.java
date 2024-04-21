package ru.net.serbis.tools.data.mark;

import java.util.*;

public class MarkSorter implements Comparator<Mark>
{
    @Override
    public int compare(Mark p1, Mark p2)
    {
        return p1.getName().compareTo(p2.getName());
    }
}
