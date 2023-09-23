package ru.net.serbis.tools.fragment;

import android.app.*;
import android.content.*;
import android.os.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;

public class ThemesFragment extends ResourcesFragment
{
    protected Resource resource;

    public ThemesFragment()
    {
    }

    public ThemesFragment(Activity context)
    {
        this(context, null);
    }
    
    public ThemesFragment(Activity context, Resource resource)
    {
        this.resource = resource;
        show(context.getFragmentManager(), "resources");
    }

    @Override
    protected void initTop(Bundle state)
    {
        if (resource == null)
        {
            super.initTop(state);
        }
    }

    @Override
    protected ResourcesDialog createDialog(Context context)
    {
        if (resource == null)
        {
            return new ThemesDialog(context)
            {
                @Override
                public void reopen(Resource resource)
                {
                    reopenFragment(resource);
                }
            };
        }
        return new ThemesDialog(context, resource.getId(), resource)
        {
            @Override
            public void reopen(Resource resource)
            {
                reopenFragment(resource);
            }
        };
    }
    
    protected void reopenFragment(Resource resource)
    {
        dismiss();
        new ThemesFragment(getActivity(), resource);
    }
}
