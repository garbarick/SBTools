package ru.net.serbis.tools.fragment;

import android.app.*;
import android.content.*;
import ru.net.serbis.tools.dialog.*;

public class XmlFragment extends ResourcesFragment
{
    public XmlFragment()
    {
    }

    public XmlFragment(Activity context)
    {
        super(context);
    }

    @Override
    protected ResourcesDialog createDialog(Context context)
    {
        return new XmlDialog(context);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        dialog.createNeutralButton();
    }
}
