package ru.net.serbis.tools.resource;

import java.lang.reflect.*;
import java.util.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ResourceLoader
{
    private static final ResourceLoader instance = new ResourceLoader();
    private boolean loaded;
    private Map<Integer, Resource> resources = new HashMap<Integer, Resource>();

    public static ResourceLoader get()
    {
        return instance;
    }

    public void load()
    {
        if (loaded)
        {
            return;
        }
        for (ResType type : ResType.class.getEnumConstants())
        {
            load(type);
        }
        loaded = true;
    }

    private void load(ResType type)
    {
        for (Field field : type.getClazz().getFields())
        {
            try
            {
                int id = field.get(null);
                resources.put(id, new Resource(field.getName(), id, type));
            }
            catch (Exception e)
            {
                Log.error(this, e);
            }
        }
    }

    public List<Resource> get(ResType type)
    {
        List<Resource> result = new ArrayList<Resource>();
        for (Resource resource : resources.values())
        {
            if (type.equals(resource.getType()))
            {
                result.add(resource);
            }
        }
        Collections.sort(result, new ResourceComparator());
        return result;
    }

    public Resource get(int id)
    {
        return resources.get(id);
    }
}
