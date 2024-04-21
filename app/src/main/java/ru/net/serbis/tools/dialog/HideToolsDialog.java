package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.tools.util.*;

public class HideToolsDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener
{
    private ListView list;
    private HideToolsAdapter adapter;
    private Activity context;

    public HideToolsDialog(Activity context)
    {
        super(context);
        this.context = context;
        setTitle(R.string.hide_tools);
        list = new ListView(context);
        adapter = new HideToolsAdapter(context);
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
            Tool tool = adapter.getTool(i);
            list.setItemChecked(i, tool.isHidden());
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
        Set<String> hides = new TreeSet<String>();
        for (int i = 0; i < adapter.getCount(); i ++)
        {
            Tool tool = adapter.getTool(i);
            tool.setHidden(list.isItemChecked(i));
            if (list.isItemChecked(i))
            {
                String name = Strings.get().get(tool.getNameId());
                hides.add(name);
            }
        }
        Params.HIDE_TOOLS.saveValue(hides);
        if (context instanceof ToolsActivity)
        {
            ((ToolsActivity) context).init();
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
