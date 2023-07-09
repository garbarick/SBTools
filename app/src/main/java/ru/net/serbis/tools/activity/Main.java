package ru.net.serbis.tools.activity;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.tool.*;

public class Main extends Activity implements View.OnClickListener, TaskCallback
{
    private LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        main = UITool.findView(this, R.id.main);
        ToolsAdapter adapter = new ToolsAdapter(this, main);
        initButtons(adapter.getButtonIds());
    }

    private void initButtons(List<Integer> ids)
    {
        for (int id : ids)
        {
            initButton(id);
        }
    }

    private void initButton(int id)
    {
        View button = UITool.findView(this, id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (!main.isEnabled())
        {
            return;
        }
        switch (view.getId())
        {
            case R.id.zip_dir:
                zipDir();
                break;

            case R.id.zip_dir_set:
                new ParamsDialog(this, R.string.zip_dir_set_title, Params.ZIP_DIR_PARAMS);
                break;

            case R.id.sys_drawables:
                new SysDrawables(this);
                break;
        }
    }

    private void zipDir()
    {
        main.setEnabled(false);
        new ZipTask(this, this).execute();
    }

    @Override
    public void progress(int value)
    {
        ProgressBar bar = UITool.findView(this, R.id.progress);
        bar.setProgress(value);
    }

    @Override
    public void onResult(boolean result, TaskError error)
    {
        if (!result)
        {
            UITool.toast(this, error);
        }
        main.setEnabled(true);
    }
}
