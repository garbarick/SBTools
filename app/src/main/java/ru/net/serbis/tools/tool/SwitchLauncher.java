package ru.net.serbis.tools.tool;

import android.content.*;
import android.content.pm.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;

public class SwitchLauncher extends Tool
{
    @Override
    public int getNameId()
    {
        return R.string.switch_launcher;
    }

    @Override
    public int getImageId()
    {
        return R.drawable.switch_launcher;
    }

    @Override
    protected boolean hasSettings()
    {
        return false;
    }

    @Override
    public void tool()
    {
        PackageManager manager = context.getPackageManager();

        ComponentName component = new ComponentName(context, FakeLauncher.class);
        manager.setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        Intent selector = new Intent(Intent.ACTION_MAIN);
        selector.addCategory(Intent.CATEGORY_HOME);
        selector.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(selector);

        manager.setComponentEnabledSetting(component, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT, PackageManager.DONT_KILL_APP);
    }
}
