package ru.net.serbis.tools.util;

import java.io.*;
import java.util.*;
import org.json.*;

public class JsonTool
{
    private static final JsonTool instance = new JsonTool();

    public static JsonTool get()
    {
        return instance;
    }

    public String toJsonString(Collection<String> list)
    {
        return toJson(list).toString();
    }

    public JSONArray toJson(Collection list)
    {
        JSONArray result = new JSONArray();
        for (Object item : list)
        {
            result.put(item);
        }
        return result;
    }

    public Set<String> toSet(String json)
    {
        try
        {
            return toSet(new JSONArray(json));
        }
        catch (Exception e)
        {
            Log.error(this, e);
            return new TreeSet<String>();
        }
    }

    public Set<String> toSet(JSONArray json) throws Exception
    {
        Set<String> result = new TreeSet<String>();
        for (int i = 0; i < json.length(); i++)
        {
            String item = json.getString(i);
            result.add(item);
        }
        return result;
    }

    public void putValue(JSONObject json, String name, Object value)
    {
        try
        {
            if (value instanceof Collection)
            {
                JSONArray values = new JSONArray();
                for (Object item : ((Collection) value))
                {
                    values.put(item);
                }
                json.put(name, values);
            }
            else
            {
                json.put(name, value);
            }
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
    }

    public JSONObject parse(File file)
    {
        String content = IOTool.get().readFile(file);
        return parse(content);
    }

    public JSONObject parse(String text)
    {
        try
        {
            return new JSONObject(text);
        }
        catch (Exception e)
        {
            Log.error(this, e);
            return new JSONObject();
        }
    }
}
