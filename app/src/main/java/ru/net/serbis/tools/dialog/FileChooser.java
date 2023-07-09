package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.widget.*;
import ru.net.serbis.tools.adapter.*;

public abstract class FileChooser extends AlertDialog.Builder implements DialogInterface.OnClickListener
{
	private FilesAdapter adapter;

	public FileChooser(Context context, int title, boolean onlyFolder)
	{
		super(context);

		ListView list = new ListView(context);
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
                onChoose(adapter.getFolder().getAbsolutePath());
                break;
        }
    }

	public abstract void onChoose(String path);
}
