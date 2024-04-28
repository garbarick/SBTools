package ru.net.serbis.tools.fragment;

import android.app.*;
import android.content.*;
import android.os.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.util.*;

public class ActivitiesFragment extends DialogFragment
{
    private ActivitiesDialog dialog;
    private int position;

    public ActivitiesFragment()
    {
    }

    public ActivitiesFragment(Activity context)
    {
        show(context.getFragmentManager(), "activities");
    }

    @Override
    public Dialog onCreateDialog(Bundle state)
    {
        position = UITool.get().getBundle(state, "position", 0);
        dialog = new ActivitiesDialog(getActivity());
        return dialog.create();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        dialog.initButtons();
        dialog.initItems();
        dialog.setPosition(position);
        dialog.setOnDismissListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle state)
    {
        state.putInt("position", dialog.getPosition());
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);
        ((ToolsActivity) getActivity()).closeTool();
    }
}
