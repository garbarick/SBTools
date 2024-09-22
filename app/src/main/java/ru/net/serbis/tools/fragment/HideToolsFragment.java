package ru.net.serbis.tools.fragment;

import android.app.*;
import android.os.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.bean.*;

public class HideToolsFragment extends DialogFragment
{
    private HideToolsDialog dialog;

    public HideToolsFragment()
    {
    }

    public HideToolsFragment(Activity context)
    {
        show(context.getFragmentManager(), "hideTools");
    }

    @Override
    public Dialog onCreateDialog(Bundle state)
    {
        dialog = new HideToolsDialog(getActivity());
        Holder<Integer, Boolean> values = UITool.get().getBundle(state, "values", new Holder<Integer, Boolean>());
        dialog.setValues(values);
        return dialog.create();
    }

    @Override
    public void onSaveInstanceState(Bundle state)
    {
        state.putSerializable("values", dialog.getValues());
    }
}
