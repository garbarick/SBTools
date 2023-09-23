package ru.net.serbis.tools.fragment;

import android.app.*;
import android.os.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.util.*;

public class ParamsFragment extends DialogFragment
{
    private int titleId;
    private String params;
    private ParamsDialog dialog;

    public ParamsFragment()
    {
    }

    public ParamsFragment(Activity context, int titleId, Param[] params)
    {
        this.titleId = titleId;
        this.params = Params.PARAMS.getKey(params);
        show(context.getFragmentManager(), "params");
    }

    @Override
    public Dialog onCreateDialog(Bundle state)
    {
        titleId = UITool.get().getBundle(state, "titleId", titleId);
        params = UITool.get().getBundle(state, "params", params);
        dialog = new ParamsDialog(getActivity(), titleId, Params.PARAMS.get(params));
        Holder<Integer, String> values = UITool.get().getBundle(state, "values", new Holder<Integer, String>());
        dialog.setValues(values);
        return dialog.create();
    }

    @Override
    public void onSaveInstanceState(Bundle state)
    {
        state.putInt("titleId", titleId);
        state.putString("params", params);
        state.putSerializable("values", dialog.getValues());
    }

    public void updateValue(Param param, Object value)
    {
        dialog.updateValue(param, value);
    }
}
