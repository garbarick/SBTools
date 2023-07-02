package ru.net.serbis.tools.activity;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.tool.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.data.*;

public class Main extends Activity implements View.OnClickListener, TaskCallback
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initButtons();
    }

    private void initButtons()
    {
        Button button = UITool.findView(this, R.id.zip_dir);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (!UITool.isEnabled(this, R.id.buttons))
        {
            return;
        }
        switch (view.getId())
        {
            case R.id.zip_dir:
                zipDir();
                break;
        }
    }

    private void zipDir()
    {
        UITool.disable(this, R.id.buttons);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(dir, dir.getName() + ".zip");
        new ZipTask(this, this).execute(dir, file);
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
        UITool.enable(this, R.id.buttons);
    }
}
