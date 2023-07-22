package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public abstract class ResoursesDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener, View.OnClickListener
{
    protected ListView list;
    protected ResoursesAdapter adapter;
    protected AlertDialog dialog;
    private Button neutralButton;

    public ResoursesDialog(Context context, int title)
    {
        super(context);
        init(context, title, null);
    }

    public ResoursesDialog(Context context, int title, int theme, Resource resource)
    {
        super(context, theme);
        init(context, title, resource);
    }

    protected void init(Context context, int title, Resource resource)
    {
        setTitle(title);
        initList(context, resource);
        setView(list);
        initButtons();
        dialog = show();
    }

    protected void initList(Context context, Resource resource)
    {
        list = new ListView(context);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter = createAdapter();
        list.setAdapter(adapter);
        if (resource != null)
        {
            int position = adapter.getPosition(resource);
            list.setSelection(position);
            adapter.setSelection(position);
        }
    }

    protected void initButtons()
    {
        setPositiveButton(android.R.string.copy, this);
        setNegativeButton(android.R.string.cancel, this);
    }

    protected void initNeutralButton(int textId)
    {
        setNeutralButton(textId, null);
    }

    protected void creatNeutralButton()
    {
        neutralButton = dialog.getButton(Dialog.BUTTON_NEUTRAL);
        neutralButton.setOnClickListener(this);
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
    
    @Override
    public void onClick(View view)
    {
        if (view == neutralButton)
        {
            neutral();
        }
    }
}
