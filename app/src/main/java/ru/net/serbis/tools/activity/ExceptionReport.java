package ru.net.serbis.tools.activity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ExceptionReport extends Activity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exception_report);

        EditText edit = UITool.findView(this, R.id.text);
        edit.setKeyListener(null);
        edit.setTextIsSelectable(true);

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.THROWABLE))
        {
            Throwable error = (Throwable) intent.getSerializableExtra(Constants.THROWABLE);
            edit.setText(SysTool.errorToText(error));
        }
        UITool.initButtons(this, this, R.id.ok, R.id.cancel);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.ok:
                startMain();
                break;

            case R.id.cancel:
                finish();
                break;
        }
    }

    private void startMain()
    {
        Intent intent = new Intent(this, Main.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
