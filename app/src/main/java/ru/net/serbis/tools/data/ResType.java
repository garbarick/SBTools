package ru.net.serbis.tools.data;

public enum ResType
{
    STRING(android.R.string.class),
    DRAWABLE(android.R.drawable.class),
    LAYOUT(android.R.layout.class),
    COLOR(android.R.color.class),
    STYLE(android.R.style.class),
    ATTR(android.R.attr.class),
    ID(android.R.id.class);

    private Class clazz;

    private ResType(Class clazz)
    {
        this.clazz = clazz;
    }

    public Class getClazz()
    {
        return clazz;
    }

    public String getValue()
    {
        return name().toLowerCase();
    }
}
