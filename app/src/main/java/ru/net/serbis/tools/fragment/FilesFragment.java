package ru.net.serbis.tools.fragment;

import android.app.*;
import android.os.*;
import java.util.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.util.*;

public class FilesFragment extends DialogFragment
{
    private FilesDialog dialog;
    private Set<String> pathes;

    public FilesFragment()
    {
    }

    public FilesFragment(Activity context)
    {
        show(context.getFragmentManager(), "files");
    }

    @Override
    public Dialog onCreateDialog(Bundle state)
    {
        ArrayList<CharSequence> data = UITool.get().getBundle(state, FilesDialog.KEY, new ArrayList<CharSequence>());
        pathes = SysTool.get().getSet(data);
        dialog = new FilesDialog(getActivity(), pathes);
        return dialog.create();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        dialog.initButtons();
    }

    @Override
    public void onSaveInstanceState(Bundle state)
    {
        ArrayList<CharSequence> data = new ArrayList<CharSequence>();
        data.addAll(dialog.getPathes());
        state.putCharSequenceArrayList(FilesDialog.KEY, data);
    }
}
