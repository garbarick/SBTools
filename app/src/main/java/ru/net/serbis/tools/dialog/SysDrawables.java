package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class SysDrawables extends AlertDialog.Builder implements DialogInterface.OnClickListener
{
    private ListView list;
    private ResoursesAdapter adapter;

    public SysDrawables(Context context)
    {
        super(context);

        setTitle(R.string.sys_drawables);
        list = new ListView(context);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter = new ResoursesAdapter(context);
        list.setAdapter(adapter);
        setView(list);
        
        setPositiveButton(android.R.string.ok, this);
        setNegativeButton(android.R.string.cancel, this);

        show();
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
        int selected = list.getCheckedItemPosition();
        if (selected > -1)
        {
            Resource resource = adapter.getItem(selected);
            SysTool.setClipBoard(getContext(), R.string.resource_clip_label, resource.getName());
            String message = getContext().getResources().getString(R.string.copied_to_clipboard);
            UITool.toast(getContext(), String.format(message, resource.getName()));
        }
    }
}
