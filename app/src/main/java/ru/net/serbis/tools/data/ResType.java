package ru.net.serbis.tools.data;

public enum ResType
{
    STRING,
    DRAWABLE,
    LAYOUT,
    COLOR,
    STYLE;
    
    public String getValue()
    {
        return name().toLowerCase();
    }
}
