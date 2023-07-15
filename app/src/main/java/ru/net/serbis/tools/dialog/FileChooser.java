package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.widget.*;
import java.io.*;
import ru.net.serbis.tools.adapter.*;

public abstract class FileChooser extends AlertDialog.Builder implements DialogInterface.OnClickListener
{
    private ListView list;
	private FilesAdapter adapter;

	public FileChooser(Context context, int title, boolean onlyFolder)
	{
		super(context);

		list = new ListView(context);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		adapter = new FilesAdapter(context, this, onlyFolder);
		list.setAdapter(adapter);
		list.setOnItemClickListener(adapter);
		adapter.initFiles();

		setTitle(title);
		setView(list);
		setPositiveButton(android.R.string.ok,this);
		setNegativeButton(android.R.string.cancel, this);
		show();
	}
    
    public void onClick(DialogInterface dialog, int id)
    {
        switch(id)
        {
            case Dialog.BUTTON_POSITIVE:
                positive();
                break;
        }
    }

    private void positive()
    {
        File file = adapter.getSelected();
        if (file != null)
        {
            onChoose(file.getAbsolutePath());
        }
    }

	public abstract void onChoose(String path);

    public ListView getList()
    {
        return list;
    }
}
