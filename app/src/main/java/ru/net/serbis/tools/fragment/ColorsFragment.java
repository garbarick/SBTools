package ru.net.serbis.tools.fragment;

import android.app.*;
import android.content.*;
import ru.net.serbis.tools.dialog.*;

public class ColorsFragment extends ResourcesFragment
{
    public ColorsFragment()
    {
    }

    public ColorsFragment(Activity context)
    {
        super(context);
    }

    @Override
    protected ResourcesDialog createDialog(Context context)
    {
        return new ColorsDialog(context);
    }
}
