package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.bean.*;

import ru.net.serbis.tools.R;
import android.os.*;

public class PermissionsDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener
{
    private ListView list;
    private PermisssionsAdapter adapter;
    private Activity context;
    private Permissions permissions = new Permissions();

    public PermissionsDialog(Activity context)
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
    }

    private void checkItems()
    {
        for (int i = 0; i < adapter.getCount(); i ++)
        {
            String permission = adapter.getItem(i);
            list.setItemChecked(i, permissions.isGrantedPermission(context, permission));
        }
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
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            UITool.get().notImplementedYet();
            return;
        }
        for (int i = 0; i < adapter.getCount(); i ++)
        {
            String permission = adapter.getItem(i);
            if (list.isItemChecked(i) && !permissions.isGrantedPermission(context, permission))
            {
                context.requestPermissions(new String[]{permission}, 200);
            }
        }
    }

    public Holder<Integer, Boolean> getValues()
    {
        Holder<Integer, Boolean> result = new Holder<Integer, Boolean>();
        for (int i = 0; i < adapter.getCount(); i ++)
        {
            result.put(i, list.isItemChecked(i));
        }
        return result;
    }

    public void setValues(Holder<Integer, Boolean> values)
    {
        for (Map.Entry<Integer, Boolean> entry : values.entrySet())
        {
            list.setItemChecked(entry.getKey(), entry.getValue());
        }
    }
}
