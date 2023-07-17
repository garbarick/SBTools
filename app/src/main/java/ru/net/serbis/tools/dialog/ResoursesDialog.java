package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public abstract class ResoursesDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener
{
    protected ListView list;
    protected ResoursesAdapter adapter;
    protected AlertDialog dialog;

    public ResoursesDialog(Context context, int title)
    {
        super(context);

        setTitle(title);
        list = new ListView(context);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter = createAdapter();
        list.setAdapter(adapter);
        setView(list);
        initButtons();
        dialog = show();
    }

    protected void initButtons()
    {
        setPositiveButton(android.R.string.copy, this);
        setNegativeButton(android.R.string.cancel, this);
    }

    protected abstract ResoursesAdapter createAdapter();

    @Override
    public void onClick(DialogInterface dialog, int id)
    {
        switch(id)
        {
            case Dialog.BUTTON_POSITIVE:
                positive();
                break;
            case Dialog.BUTTON_NEGATIVE:
                negative();
                break;
            case Dialog.BUTTON_NEUTRAL:
                neutral();
                break;
        }
    }

    protected void positive()
    {
        int selected = list.getCheckedItemPosition();
        if (selected > -1)
        {
            Resource resource = adapter.getItem(selected);
            SysTool.setClipBoard(getContext(), R.string.resource_clip_label, resource.getName(getContext()));
        }
    }

    protected void negative()
    {
    }

    protected void neutral()
    {
    }
}
