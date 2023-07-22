package ru.net.serbis.tools.resource;

import android.content.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.data.*;

public class OpenerXml
{
    public void open(Context context, Resource resource)
    {
        Intent intent = new Intent(context, ViewXml.class);
        intent.putExtra(Constants.TITLE, resource.getName());
        intent.putExtra(Constants.RESOURCE, resource);
        context.startActivity(intent);
    }
}
