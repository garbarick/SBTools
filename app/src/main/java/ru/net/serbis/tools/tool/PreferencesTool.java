package ru.net.serbis.tools.tool;

import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.util.*;

public class PreferencesTool extends Tool
{
    @Override
    public int getNameId()
    {
        return R.string.preferences;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }

    @Override
    public void tool()
    {
        List<Param> paramList = new ArrayList<Param>();
        for (String name : new TreeSet<String>(Preferences.get().getNames()))
        {
            paramList.add(new ParamViewer(name, Preferences.get().get(name)));
        }
        String name = Strings.get().get(R.string.preferences);
        Param[] params = paramList.toArray(new Param[paramList.size()]);
        Params.PARAMS.put(name, params);
        new ParamsFragment(context, R.string.preferences, params, false, false);
    }
}
