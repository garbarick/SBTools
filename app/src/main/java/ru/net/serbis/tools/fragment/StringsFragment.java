package ru.net.serbis.tools.fragment;

import android.app.*;
import android.content.*;
import ru.net.serbis.tools.dialog.*;

public class StringsFragment extends ResourcesFragment
{
    public StringsFragment()
    {
    }

    public StringsFragment(Activity context)
    {
        super(context);
    }

    @Override
    protected ResourcesDialog createDialog(Context context)
    {
        return new StringsDialog(context);
    }
}
