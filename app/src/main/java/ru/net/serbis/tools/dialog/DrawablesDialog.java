package ru.net.serbis.tools.dialog;

import android.content.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;

public class DrawablesDialog extends ResourcesDialog
{
    public DrawablesDialog(Context context)
    {
        super(context, R.string.sys_drawables);
    }

    @Override
    protected ResoursesAdapter createAdapter()
    {
        return new ResoursesImgAdapter(getContext());
    }
}
