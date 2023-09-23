package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.resource.*;

public class XmlDialog extends ResourcesDialog
{
    public XmlDialog(Context context)
    {
        super(context, R.string.sys_layouts);
    }

    @Override
    protected ResoursesAdapter createAdapter()
    {
        return new ResoursesXmlAdapter(getContext());
    }

    @Override
    protected void initButtons()
    {
        super.initButtons();
        initNeutralButton(R.string.view);
    }

    @Override
    protected void neutral()
    {
        int selected = list.getCheckedItemPosition();
        if (selected > -1)
        {
            Resource resource = adapter.getItem(selected);
            new OpenerXml().open(getContext(), resource);
        }
    }

    @Override
    public AlertDialog show()
    {
        super.show();
        createNeutralButton();
        return dialog;
    }
}
