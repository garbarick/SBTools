package ru.net.serbis.tools.util;

import android.content.*;
import java.io.*;
import ru.net.serbis.tools.data.*;
import java.util.*;

public class SysTool extends Util
{
    private static final SysTool instance = new SysTool();

    public static SysTool get()
    {
        return instance;
    }

    public <T> T getService(String name)
    {
        return (T) context.getSystemService(name);
    }

    public void setClipBoard(int labelId, String text)
    {
        ClipboardManager clipboard = getService(Context.CLIPBOARD_SERVICE);
        String label = context.getResources().getString(labelId);
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
    }
    
    public String errorToText(Throwable error)
    {
        StringWriter writer = new StringWriter();
        error.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    public SharedPreferences getPreferences()
    {
        return context.getSharedPreferences(Constants.APP, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getPreferencesEditor()
    {
        return getPreferences().edit();
    }

    public Set<String> getSet(Collection data)
    {
        Set<String> result = new TreeSet<String>();
        for (Object item : data)
        {
            result.add(item.toString());
        }
        return result;
    }
}
