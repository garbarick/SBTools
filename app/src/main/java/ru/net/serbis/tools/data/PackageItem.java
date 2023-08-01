package ru.net.serbis.tools.data;

import android.graphics.drawable.*;
import java.util.*;
import android.content.*;

public class PackageItem extends ActivityItem
{
    private List<ActivityItem> children = new ArrayList<ActivityItem>();
    private int count;

    public PackageItem(String label, Drawable drawable, String packageName, int count)
    {
        super(label, drawable, null, packageName);
        this.count = count;
    }

    @Override
    public String getName()
    {
        return Strings.COUNT.getValue() + count;
    }

    public void add(ActivityItem item)
    {
        children.add(item);
    }

    public List<ActivityItem> getChildren()
    {
        return children;
    }

    public boolean isReady()
    {
        return !children.isEmpty();
    }

    @Override
    public void start(Context context)
    {
    }
}
