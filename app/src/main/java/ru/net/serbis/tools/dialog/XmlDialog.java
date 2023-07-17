package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.tools.activity.*;

public class XmlDialog extends ResoursesDialog implements View.OnClickListener
{
    private Button neutralButton;

    public XmlDialog(Context context)
    {
        super(context, R.string.sys_xml);

        neutralButton = dialog.getButton(Dialog.BUTTON_NEUTRAL);
        neutralButton.setOnClickListener(this);
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
        setNeutralButton(R.string.view, null);
    }

    @Override
    protected void neutral()
    {
        int selected = list.getCheckedItemPosition();
        if (selected > -1)
        {
            Resource resource = adapter.getItem(selected);
            Intent intent = new Intent(getContext(), ViewXml.class);
            intent.putExtra(Constants.TITLE, resource.getName());
            intent.putExtra(Constants.RESOURCE, resource);
            getContext().startActivity(intent);
        }
    }

    @Override
    public void onClick(View view)
    {
        if (view == neutralButton)
        {
            neutral();
        }
    }
}
