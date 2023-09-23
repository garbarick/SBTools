package ru.net.serbis.tools.dialog;

import android.content.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;

public class ColorsDialog extends ResourcesDialog
{
    public ColorsDialog(Context context)
    {
        super(context, R.string.sys_colors);
    }

    @Override
    protected ResoursesAdapter createAdapter()
    {
        return new ResoursesColorAdapter(getContext());
    }
}
