package ru.net.serbis.tools.data;

import android.content.*;
import java.io.*;

public class Resource implements Serializable, Comparable<Resource>
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
        String name = this.name;
        if (ResType.STYLE.equals(type))
        {
            name = name.replace("_", ".");
        }
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
    
    @Override
    public int compareTo(Resource that)
    {
        return Integer.compare(id, that.id);
    }

    @Override
    public boolean equals(Object that)
    {
        if (that instanceof Resource)
        {
            return id == ((Resource)that).id ;
        }
        return super.equals(that);
    }
}
