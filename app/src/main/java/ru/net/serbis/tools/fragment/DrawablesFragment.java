package ru.net.serbis.tools.fragment;

import android.app.*;
import android.content.*;
import ru.net.serbis.tools.dialog.*;

public class DrawablesFragment extends ResourcesFragment
{
    public DrawablesFragment()
    {
    }

    public DrawablesFragment(Activity context)
    {
        super(context);
    }

    @Override
    protected ResourcesDialog createDialog(Context context)
    {
        return new DrawablesDialog(context);
    }
}
