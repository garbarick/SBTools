package ru.net.serbis.tools.data;

import android.content.*;
import java.io.*;

public class Resource implements Serializable
{
    private String name;
    private int id;
    private ResType type;

    public Resource(String name, int id, ResType type)
    {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public ResType getType()
    {
        return type;
    }
    
    private String getJavaName()
    {
        return "android.R." + type.getValue() + "." + name;
    }
    
    private String getXmlName()
    {
        return "@android:" + type.getValue() + "/" + name;
    }
    
    public String getName(Context context)
    {
        switch(Params.VIEW_TYPE.getValue(context))
        {
            case JAVA:
                return getJavaName();
            case XML:
                return getXmlName();
        }
        return "";
    }
}
