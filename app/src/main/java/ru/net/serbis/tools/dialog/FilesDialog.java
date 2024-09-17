package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public abstract class FilesDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener, View.OnClickListener, PopupMenu.OnMenuItemClickListener
{
    private ListView list;
    private FilePathAdapter adapter;
    private PopupMenu menu;
    private boolean onlyFolder;
    private boolean onlyFile;

    public FilesDialog(Activity context, int titleId, Set<String> pathes, boolean onlyFolder, boolean onlyFile)
    {
        super(context);
        this.onlyFolder = onlyFolder;
        this.onlyFile = onlyFile;

        setTitle(titleId);

        list = new ListView(context);
        setView(list);

        adapter = new FilePathAdapter(context, pathes);
        list.setAdapter(adapter);
        list.setOnItemClickListener(adapter);

        setPositiveButton(android.R.string.ok, this);
        setNeutralButton(" ", null);
        setNegativeButton(android.R.string.cancel, this);
    }

    @Override
    public AlertDialog show()
    {
        AlertDialog dialog = super.show();
        initButtons(dialog);
        return dialog;
    }

    public void initButtons(AlertDialog dialog)
    {
        Button neutral = dialog.getButton(Dialog.BUTTON_NEUTRAL);
        neutral.setId(Dialog.BUTTON_NEUTRAL);
        UITool.get().setSandwitchView(neutral);
        neutral.setOnClickListener(this);

        menu = new PopupMenu(getContext(), neutral);
        menu.getMenuInflater().inflate(R.menu.add_delete, menu.getMenu());
        menu.setOnMenuItemClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        onClick(null, view.getId());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.add:
                add();
                return true;
            case R.id.delete:
                delete();
                return true;
        }
        return false;
    }

    @Override
    public void onClick(DialogInterface dialog, int id)
    {
        switch(id)
        {
            case Dialog.BUTTON_POSITIVE:
                onResult(getPathes());
                break;
            case Dialog.BUTTON_NEUTRAL:
                menu.show();
                break;
        }
    }

    protected abstract void onResult(Set<String> result);

    public Set<String> getPathes()
    {
        return adapter.getPathes();
    }

    private void add()
    {
        new FileChooser(getContext(), R.string.choose_dir, onlyFolder, onlyFile)
        {
            @Override
            public void onChoose(String path)
            {
                adapter.add(path);
            }
        };
    }

    private void delete()
    {
        adapter.deleteChecked();
    }
}
