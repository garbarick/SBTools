package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.util.*;

public class FilesDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener, View.OnClickListener, PopupMenu.OnMenuItemClickListener
{
    private AlertDialog dialog;
    private ListView list;
    private FilePathAdapter adapter;
    private Button neutral;
    private PopupMenu menu;
    public static final String KEY = "clearTrash";

    public FilesDialog(Activity context, Set<String> pathes)
    {
        super(context);

        setTitle(R.string.clear_trash_set);

        list = new ListView(context);
        setView(list);
        if (pathes == null || pathes.isEmpty())
        {
            pathes = SysTool.get().getPreferences(context).getStringSet(KEY, new TreeSet<String>());
        }
        adapter = new FilePathAdapter(context, pathes);
        list.setAdapter(adapter);
        list.setOnItemClickListener(adapter);

        setPositiveButton(android.R.string.ok, this);
        setNeutralButton(" ", null);
        setNegativeButton(android.R.string.cancel, this);
    }

    public FilesDialog(Activity context)
    {
        this(context, null);
    }

    @Override
    public AlertDialog create()
    {
        dialog = super.create();
        return dialog;
    }

    @Override
    public AlertDialog show()
    {
        dialog = super.show();
        initButtons();
        return dialog;
    }

    public void initButtons()
    {
        neutral = dialog.getButton(Dialog.BUTTON_NEUTRAL);
        Drawable sandwitch = getContext().getResources().getDrawable(R.drawable.sandwitch);
        sandwitch.setBounds(28, 0, 92, 64);
        neutral.setCompoundDrawables(sandwitch, null, null, null);
        neutral.setOnClickListener(this);

        menu = new PopupMenu(getContext(), neutral);
        menu.getMenuInflater().inflate(R.menu.files_menu, menu.getMenu());
        menu.setOnMenuItemClickListener(this);
    }

    @Override
    public void onClick(View button)
    {
        if (button == neutral)
        {
            menu.show();
        }
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
                positive();
                break;
        }
    }

    private void positive()
    {
        SharedPreferences.Editor editor = SysTool.get().getPreferencesEditor(getContext());
        editor.putStringSet(KEY, getPathes());
        editor.commit();
    }

    public Set<String> getPathes()
    {
        return adapter.getPathes();
    }

    private void add()
    {
        new FileChooser(getContext(), R.string.choose_dir, false, false)
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
