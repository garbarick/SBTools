package ru.net.serbis.tools.util;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;

public class UITool
{
    private static final UITool instance = new UITool();

    public static UITool get()
    {
        return instance;
    }

    public <T> T findView(View view, int id)
    {
        return (T) view.findViewById(id);
    }

    public <T> T findView(Activity view, int id)
    {
        return (T) view.findViewById(id);
    }

    public String getEditText(Activity activity, int id)
    {
        EditText text = findView(activity, id);
        return text.getText().toString();
    }

    public void hide(Activity activity, int id)
    {
        View view = findView(activity, id);
        view.setVisibility(View.GONE);
    }

    public void show(Activity activity, int id)
    {
        View view = findView(activity, id);
        view.setVisibility(View.VISIBLE);
    }

    public void enable(Activity activity, int id)
    {
        View view = findView(activity, id);
        view.setEnabled(true);
    }

    public void disable(Activity activity, int id)
    {
        View view = findView(activity, id);
        view.setEnabled(false);
    }

    public boolean isEnabled(Activity activity, int id)
    {
        View view = findView(activity, id);
        return view.isEnabled();
    }

    public void toast(final Context context, final String text)
    {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(
            new Runnable() {
                public void run()
                {
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                }
            });
    }
    
    public void toast(Context context, int code, String text)
    {
        toast(context, code + ": " + text);
    }

    public void toast(Context context, TaskError error)
    {
        toast(context, error.getCode(), error.getMessage());
    }

    public int getPercent(long max, long cur)
    {
        Double percent = 100.0 / max * cur;
        return percent.intValue();
    }

    public void initButtons(Activity context, View.OnClickListener tool, int ... ids)
    {
        for (int id : ids)
        {
            View button = findView(context, id);
            button.setOnClickListener(tool);
        }
    }
    
    public void setProgress(Context context, boolean progress)
    {
        App app = (App) context.getApplicationContext();
        app.setProgress(progress);
    }
    
    public boolean isProgress(Context context)
    {
        App app = (App) context.getApplicationContext();
        return app.isProgress();
    }
}
