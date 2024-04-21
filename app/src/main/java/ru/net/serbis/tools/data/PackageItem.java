package ru.net.serbis.tools.data;

import android.content.*;
import android.graphics.drawable.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

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
        return Strings.get().get(R.string.count) + count;
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
