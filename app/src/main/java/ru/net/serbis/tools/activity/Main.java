package ru.net.serbis.tools.activity;

import android.app.*;
import android.os.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.util.*;

public class Main extends Activity
{
    private LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        main = UITool.findView(this, R.id.main);
        new ToolsAdapter(this, main);
    }
}
