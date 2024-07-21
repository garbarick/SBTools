package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public abstract class ResourcesDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener, View.OnClickListener
{
    protected ListView list;
    protected ResoursesAdapter adapter;
    protected AlertDialog dialog;
    private Button neutralButton;

    public ResourcesDialog(Context context, int title, Resource resource)
    {
        super(context);
        init(context, title, resource);
    }

    public ResourcesDialog(Context context, int title)
    {
        this(context, title, null);
    }

    public ResourcesDialog(Context context, int title, int theme, Resource resource)
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
    }

    @Override
    public AlertDialog create()
    {
        dialog = super.create();
        return dialog;
    }

    @Override
    public AlertDialog show()
    {
        dialog = super.show();
        return dialog;
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
    
    public void createNeutralButton()
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
        Resource resource = getSelected();
        if (resource != null)
        {
            SysTool.get().setClipBoard(R.string.resource_clip_label, resource.getName(getContext()));
        }
        else
        {
            UITool.get().toast("resource is null");
        }
    }
    
    public Resource getSelected()
    {
        int selected = list.getCheckedItemPosition();
        if (selected == -1)
        {
            selected = adapter.getSelection();
        }
        if (selected > -1)
        {
            return adapter.getItem(selected);
        }
        return null;
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

    public int getTop()
    {
        return list.getFirstVisiblePosition();
    }

    public void setTop(int top)
    {
        list.setSelection(top);
    }
}
