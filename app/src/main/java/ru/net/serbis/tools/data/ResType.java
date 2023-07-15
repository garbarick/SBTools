package ru.net.serbis.tools.data;

public enum ResType
{
    STRING,
    DRAWABLE;
    
    public String getValue()
    {
        return name().toLowerCase();
    }
}
