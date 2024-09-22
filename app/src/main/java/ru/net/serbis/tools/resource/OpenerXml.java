package ru.net.serbis.tools.resource;

import android.content.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.*;

public class OpenerXml
{
    public void open(Context context, Resource resource)
    {
        Intent intent = new Intent(context, ViewXml.class);
        intent.putExtra(UtilsConstants.TITLE, resource.getName());
        intent.putExtra(Constants.RESOURCE, resource);
        context.startActivity(intent);
    }
}
