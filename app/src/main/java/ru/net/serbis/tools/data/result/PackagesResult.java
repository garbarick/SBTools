package ru.net.serbis.tools.data.result;

import java.util.*;
import ru.net.serbis.tools.util.*;

public class PackagesResult extends Result
{
    private Set<String> result = new TreeSet<String>();

    public PackagesResult(Progress progress)
    {
        super(progress);
    }

    public void add(String value)
    {
        result.add(value);
    }

    public Set<String> get()
    {
        return result;
    }
}
