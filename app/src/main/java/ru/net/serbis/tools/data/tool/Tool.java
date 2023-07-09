package ru.net.serbis.tools.data.tool;

import java.util.*;

public class Tool
{
    private int layoutId;
    private Integer[] buttonIds;

    public Tool(int layoutId, Integer[] buttonIds)
    {
        this.layoutId = layoutId;
        this.buttonIds = buttonIds;
    }

    public int getLayoutId()
    {
        return layoutId;
    }

    public List<Integer> getButtonIds()
    {
        return Arrays.asList(buttonIds);
    }
}
