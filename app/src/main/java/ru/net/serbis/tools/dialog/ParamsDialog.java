package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.view.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.param.*;
import android.widget.*;

public class ParamsDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener
{
    private ParamsAdapter adapter;

    public ParamsDialog(Context context, int titleId, Param[] params)
    {
        super(context);

        setTitle(titleId);

        View view = LayoutInflater.from(context).inflate(R.layout.params, null, false);
		adapter = new ParamsAdapter(context, view, params);
        setView(view);

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
                adapter.saveValues();
                break;
        }
    }
}
