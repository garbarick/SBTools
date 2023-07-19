package ru.net.serbis.tools.dialog;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;

public class ThemesDialog extends ResoursesDialog implements AdapterView.OnItemClickListener
{
    public ThemesDialog(Context context)
    {
        super(context, R.string.sys_themes);
    }

    public ThemesDialog(Context context, int theme, Resource resource)
    {
        super(context, R.string.sys_themes, theme, resource);
    }

    @Override
    protected ResoursesAdapter createAdapter()
    {
        return new ResoursesThemeAdapter(getContext());
    }

    @Override
    protected void initList(Context context, Resource resource)
    {
        super.initList(context, resource);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Resource resource = adapter.getItem(position);
        dialog.dismiss();
        new ThemesDialog(getContext(), resource.getId(), resource);
    }
}
