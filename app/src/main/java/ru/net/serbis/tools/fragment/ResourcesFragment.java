package ru.net.serbis.tools.fragment;

import android.app.*;
import android.content.*;
import android.os.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.utils.*;

public abstract class ResourcesFragment extends DialogFragment
{
    protected ResourcesDialog dialog;

    public ResourcesFragment()
    {
    }

    public ResourcesFragment(Activity context)
    {
        show(context.getFragmentManager(), "resources");
    }
    
    @Override
    public Dialog onCreateDialog(Bundle state)
    {
        dialog = createDialog(getActivity());
        initTop(state);
        return dialog.create();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        dialog.setOnDismissListener(this);
    }

    protected void initTop(Bundle state)
    {
        int top = UITool.get().getBundle(state, "top", 0);
        dialog.setTop(top);
    }

    protected abstract ResourcesDialog createDialog(Context context);

    @Override
    public void onSaveInstanceState(Bundle state)
    {
        state.putInt("top", dialog.getTop());
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        ((ToolsActivity) getActivity()).closeTool();
    }
}
