package ru.net.serbis.tools.data;

import android.content.*;
import android.graphics.drawable.*;
import ru.net.serbis.tools.util.*;

public class ActivityItem implements Comparable<ActivityItem>
{
    protected String label;
    protected Drawable drawable;
    protected String name;
    protected String packageName;

    public ActivityItem(String label, Drawable drawable, String name, String packageName)
    {
        this.label = label;
        this.drawable = drawable;
        this.name = name;
        this.packageName = packageName;
    }

    public String getLabel()
    {
        return label;
    }

    public String getName()
    {
        return name;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public Drawable getDrawable()
    {
        return drawable;
    }

    @Override
    public int compareTo(ActivityItem that)
    {
        return label.compareTo(that.label);
    }

    protected Intent getIntent()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.setComponent(new ComponentName(packageName, name));
        return intent;
    }

    public void start(Context context)
    {
        try
        {
            context.startActivity(getIntent());
        }
        catch(Throwable e)
        {
            Log.error(this, e);
        }
    }
}
