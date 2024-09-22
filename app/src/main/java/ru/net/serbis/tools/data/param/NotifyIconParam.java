package ru.net.serbis.tools.data.param;

import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.resource.*;
import ru.net.serbis.utils.param.*;

public class NotifyIconParam extends SpinnerParam<Resource>
{
    public NotifyIconParam()
    {
        super(R.string.notify_icon, null, null);
    }

    @Override
    public String getName()
    {
        values = ResourceLoader.get().get(ResType.LOCAL_DRAWABLE).toArray(new Resource[0]);
        value = values[0];
        return super.getName();
    }

    @Override
    public String typeToString(Resource value)
    {
        return value.getName();
    }

    @Override
    public Resource stringToType(String value)
    {
        return ResourceLoader.get().get(ResType.LOCAL_DRAWABLE, value);
    }

    @Override
    protected ArrayAdapter<Resource> getAdapter()
    {
        return new LocalResoucesAdapter(context);
    }
}
