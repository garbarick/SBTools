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
    private boolean ok;
    private boolean reset;
    private ParamsDialog dialog;

    public ParamsFragment()
    {
    }

    public ParamsFragment(Activity context, int titleId, Param[] params, boolean ok, boolean reset)
    {
        this.titleId = titleId;
        this.params = Params.PARAMS.getKey(params);
        this.ok = ok;
        this.reset = reset;
        show(context.getFragmentManager(), "params");
    }

    public ParamsFragment(Activity context, int titleId, Param[] params)
    {
        this(context, titleId, params, true, true);
    }

    @Override
    public Dialog onCreateDialog(Bundle state)
    {
        titleId = UITool.get().getBundle(state, "titleId", titleId);
        params = UITool.get().getBundle(state, "params", params);
        ok = UITool.get().getBundle(state, "ok", ok);
        reset = UITool.get().getBundle(state, "reset", reset);
        dialog = new ParamsDialog(getActivity(), titleId, Params.PARAMS.get(params), ok, reset);
        Holder<Integer, String> values = UITool.get().getBundle(state, "values", new Holder<Integer, String>());
        dialog.setValues(values);
        return dialog.create();
    }

    @Override
    public void onSaveInstanceState(Bundle state)
    {
        state.putInt("titleId", titleId);
        state.putString("params", params);
        state.putBoolean("ok", ok);
        state.putBoolean("reset", reset);
        state.putSerializable("values", dialog.getValues());
    }

    public void updateValue(Param param, Object value)
    {
        dialog.updateValue(param, value);
    }
}
