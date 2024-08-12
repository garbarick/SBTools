package ru.net.serbis.tools.view;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class FilterLayout extends LinearLayout
{
    public FilterLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        Point size = getSize();
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(size.x, MeasureSpec.getMode(widthMeasureSpec)),
            MeasureSpec.makeMeasureSpec(size.y, MeasureSpec.getMode(heightMeasureSpec))
        );
    }

    public Point getSize()
    {
        return getSize(getContext());
    }

    private Point getSize(Context context)
    {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point result = new Point();
        display.getRealSize(result);

        int barsSize = getBarsSize(context);
        result.x += barsSize;
        result.y += barsSize;

        return result;
    }

    public int getBarsSize(Context context)
    {
        return getNavigationBarHeight(context) + getStatusBarHeight(context);
    }

    private int getStatusBarHeight(Context context)
    {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    private int getNavigationBarHeight(Context context)
    {
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0 && !hasMenuKey)
        {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
