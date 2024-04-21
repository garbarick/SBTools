package ru.net.serbis.tools.tool;

import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.util.*;

public class PreferencesTool extends Tool
{
    public PreferencesTool()
    {
        super(
            R.layout.tool_preferences,
            R.id.preferences);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.preferences:
                preferences();
                break;
        }
    }

    @Override
    public int getNameId()
    {
        return R.string.preferences;
    }

    private void preferences()
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
