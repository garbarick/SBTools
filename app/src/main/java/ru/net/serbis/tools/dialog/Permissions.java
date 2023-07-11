package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.util.*;

public class Permissions extends AlertDialog.Builder implements DialogInterface.OnClickListener
{
    private ListView list;
    private PermisssionsAdapter adapter;
    private Activity context;

    public Permissions(Activity context)
    {
        super(context);
        this.context = context;
        setTitle(R.string.permissions);
        list = new ListView(context);
        adapter = new PermisssionsAdapter(context);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        checkItems();
        setView(list);

        setPositiveButton(android.R.string.ok, this);
        setNegativeButton(android.R.string.cancel, this);

        show();
    }

    private void checkItems()
    {
        for (int i = 0; i < adapter.getCount(); i ++)
        {
            String permission = adapter.getItem(i);
            list.setItemChecked(i, isGranted(permission));
        }
    }
    
    private boolean isGranted(String permission)
    {
        return context.checkCallingOrSelfPermission(permission) == 
            PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onClick(DialogInterface dialog, int id)
    {
        switch(id)
        {
            case Dialog.BUTTON_POSITIVE:
                positive();
                break;
        }
    }

    private void positive()
    {
        for (int i = 0; i < adapter.getCount(); i ++)
        {
            String permission = adapter.getItem(i);
            if (list.isItemChecked(i) && !isGranted(permission))
            {
                context.requestPermissions(new String[]{permission}, 200);
            }
        }
    }
}
