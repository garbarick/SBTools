package ru.net.serbis.tools.activity;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.util.*;

public class Main extends Activity
{
    private LinearLayout main;
    private ToolsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        main = UITool.findView(this, R.id.main);
        adapter = new ToolsAdapter(this, main);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        adapter.onActivityResult(requestCode, resultCode, data);
    }
}
