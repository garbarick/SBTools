package ru.net.serbis.tools.data;

public enum ResType
{
    STRING,
    DRAWABLE,
    LAYOUT;
    
    public String getValue()
    {
        return name().toLowerCase();
    }
}
