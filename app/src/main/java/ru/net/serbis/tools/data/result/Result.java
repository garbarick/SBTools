package ru.net.serbis.tools.data.result;

import ru.net.serbis.tools.util.*;
import ru.net.serbis.utils.*;

public class Result
{
    private int count = 0;
    private int current = 0;
    private Progress progress;

    public Result(Progress progress)
    {
        this.progress = progress;
    }

    public void count(int count)
    {
        this.count += count;
    }

    public void progress()
    {
        current ++;
        progress.progress(UITool.get().getPercent(count, current));
    }
}
