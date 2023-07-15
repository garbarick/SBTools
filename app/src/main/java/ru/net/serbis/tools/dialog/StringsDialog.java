package ru.net.serbis.tools.dialog;

import android.content.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;

public class StringsDialog extends ResoursesDialog
{
    public StringsDialog(Context context)
    {
        super(context, R.string.sys_strings);
    }

    @Override
    protected ResoursesAdapter createAdapter()
    {
        return new ResoursesStringAdapter(getContext());
    }
}
