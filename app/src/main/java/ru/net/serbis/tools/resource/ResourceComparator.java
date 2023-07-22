package ru.net.serbis.tools.resource;

import java.util.*;
import ru.net.serbis.tools.data.*;

public class ResourceComparator implements Comparator<Resource>
{
    @Override
    public int compare(Resource p1, Resource p2)
    {
        return p1.getName().compareToIgnoreCase(p2.getName());
    }
}
