package ru.net.serbis.tools.dialog;

import android.content.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.resource.*;

public class XmlDialog extends ResoursesDialog
{
    public XmlDialog(Context context, int layoutId)
    {
        super(context, layoutId);
        creatNeutralButton();
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
}
