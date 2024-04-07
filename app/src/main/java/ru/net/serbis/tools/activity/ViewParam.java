package ru.net.serbis.tools.activity;

import android.content.*;
import android.os.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

public class ViewParam extends TextActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.VALUE))
        {
            String value = intent.getStringExtra(Constants.VALUE);
            edit.setText(value);
        }
        Button button = UITool.get().findView(this, R.id.ok);
        button.setText(R.string.delete);
    }

    @Override
    protected void onOk()
    {
        String name = getTitle().toString();
        Preferences.get().setString(name, null);
        super.onOk();
    }
}
