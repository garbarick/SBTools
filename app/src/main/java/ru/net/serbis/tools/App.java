package ru.net.serbis.tools;

import android.app.*;
import android.content.*;
import ru.net.serbis.tools.connection.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.extension.share.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.utils.*;
import ru.net.serbis.utils.param.*;
import ru.net.serbis.tools.activity.*;

public class App extends Application
{
    private ExtConnection shareConnection = new ShareConnection(this);
    private boolean progress;

    @Override
    public void onCreate()
    {
        super.onCreate();

        Context context = getApplicationContext();
        init(context);
    }

    public void init(Context context)
    {
        Strings.get().set(context);
        SysTool.get().set(context);
        UITool.get().set(context);
        Preferences.get().set(context);
        Preferences.get().setApp(Constants.APP);

        initTools();
        initEnums();
        shareConnection.bind();

        ExceptionHandler.get().set(context);
        ExceptionHandler.get().setErrorActivity(ExceptionReport.class);
    }

    private void initTools()
    {
        for (Tool tool : Reflection.get().getValues(Tools.class, Tool.class).values())
        {
            String name = Strings.get().get(tool.getNameId());
            Tools.TOOLS_MAP.put(name, tool);
        }
    }

    private void initEnums()
    {
        Compression.initNames();
        NotifyType.initNames();
        Period.initNames();
        Unit.initNames();
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        shareConnection.unBind();
    }

    public ExtConnection getShareConnection()
    {
        shareConnection.bind();
        return shareConnection;
	}

    public void setProgress(boolean progress)
    {
        this.progress = progress;
    }

    public boolean isProgress()
    {
        return progress;
    }
}
