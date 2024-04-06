package ru.net.serbis.tools.util;

import android.content.*;
import java.util.*;
import ru.net.serbis.tools.data.*;

public class Preferences extends Util
{
    private static final Preferences instance = new Preferences();

    public static Preferences get()
    {
        return instance;
    }

    private SharedPreferences getPreferences()
    {
        return context.getSharedPreferences(Constants.APP, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getPreferencesEditor()
    {
        return getPreferences().edit();
    }

    public void setString(String name, String value)
    {
        SharedPreferences.Editor editor = getPreferencesEditor();
        editor.putString(name, value);
        editor.commit();
    }

    public void setStringSet(String name, Set<String> value)
    {
        SharedPreferences.Editor editor = getPreferencesEditor();
        editor.putStringSet(name, value);
        editor.commit();
    }

    public String getString(String name, String defaultValue)
    {
        return getPreferences().getString(name, defaultValue);
    }

    public Set<String> getStringSet(String name)
    {
        return getStringSet(name, new TreeSet<String>());
    }

    public Set<String> getStringSet(String name, Set<String> defaultValue)
    {
        return getPreferences().getStringSet(name, defaultValue);
    }
}
