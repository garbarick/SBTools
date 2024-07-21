package ru.net.serbis.tools.resource;

import java.util.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.*;

public class ResourceLoader
{
    private static final ResourceLoader instance = new ResourceLoader();
    private Map<Integer, Resource> resources = new HashMap<Integer, Resource>();
    private Map<ResType, Map<String, Resource>> typeNames = new HashMap<ResType, Map<String, Resource>>();

    public static ResourceLoader get()
    {
        return instance;
    }

    private void load(ResType type)
    {
        if (typeNames.containsKey(type))
        {
            return;
        }
        Map<String, Resource> names = new HashMap<String, Resource>();
        typeNames.put(type, names);
        for (Map.Entry<String, Integer> entry : Reflection.get().getValues(type.getClazz(), int.class).entrySet())
        {
            String name = entry.getKey();
            int id = entry.getValue();
            Resource resource = new Resource(name, id, type);
            resources.put(id, resource);
            names.put(name, resource);
        }
    }

    public List<Resource> get(ResType type)
    {
        load(type);
        List<Resource> result = new ArrayList<Resource>();
        result.addAll(typeNames.get(type).values());
        Collections.sort(result, new ResourceComparator());
        return result;
    }

    public Resource get(int id)
    {
        return resources.get(id);
    }

    public Resource get(ResType type, String name)
    {
        return typeNames.get(type).get(name);
    }
}
