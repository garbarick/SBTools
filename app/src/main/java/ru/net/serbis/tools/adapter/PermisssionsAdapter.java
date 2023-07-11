package ru.net.serbis.tools.adapter;

import android.content.*;
import android.content.pm.*;
import android.widget.*;
import ru.net.serbis.tools.util.*;

public class PermisssionsAdapter extends ArrayAdapter<String>
{
    public PermisssionsAdapter(Context context)
    {
        super(context, android.R.layout.simple_list_item_multiple_choice);
        initItems(context);;
    }
    
    private void initItems(Context context)
    {
        try
        {
            String packageName = context.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            addAll(packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions);
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
    }
}
