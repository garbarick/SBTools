package ru.net.serbis.tools.adapter;

import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;
import ru.net.serbis.tools.dialog.*;

public class FilesAdapter extends ArrayAdapter<File> implements AdapterView.OnItemClickListener
{
    private File folder = Environment.getExternalStorageDirectory();
    private FileChooser chooser;
    private boolean onlyFolder;

    public FilesAdapter(Context context, FileChooser chooser, boolean onlyFolder)
    {
        super(context, android.R.layout.simple_list_item_activated_1, android.R.id.text1);
        this.chooser = chooser;
        this.onlyFolder = onlyFolder;
    }

    public File getSelected()
    {
        if (onlyFolder)
        {
            return folder;
        }
        int selected = chooser.getList().getCheckedItemPosition();
        if (selected > -1)
        {
            File file = getItem(selected);
            if (file.isFile())
            {
                return file;
            }
        }
        return null;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        TextView text = (TextView) super.getView(position, view, parent);
        File file = getItem(position);
        if (file.equals(folder))
        {
            text.setText(file.getPath() + "/..");
        }
        else
        {
            text.setText(file.getName());
        }
        return text;
    }

    public void initFiles()
    {
        setNotifyOnChange(false);
        clear();

        File[] files = getFiles();
        List<File> result = getFiles(files);
        if (folder.getParentFile() != null)
        {
            result.add(0, folder);
        }

        addAll(result);
        setNotifyOnChange(true);
        notifyDataSetChanged();
        chooser.getList().setItemChecked(0, true);
	}

    private File[] getFiles()
    {
        return folder.listFiles(
            new FileFilter()
            {
                public boolean accept(File file)
                {
                    return onlyFolder && file.isDirectory() || !onlyFolder;
                }
            }
        );
	}

    private List<File> getFiles(File[] files)
    {
        List<File> result = new ArrayList<File>();
        if (files != null)
        {
            result.addAll(Arrays.asList(files));
        }
        Collections.sort(
            result,
            new Comparator<File>()
            {
                public int compare(File f1, File f2)
                {
                    if (f1.isDirectory() && f2.isFile())
                    {
                        return -1;
                    }
                    else if (f1.isFile() && f2.isDirectory())
                    {
                        return 1;
                    }
                    else
                    {
                        return f1.compareTo(f2);
                    }
                }
            }
        );
        return result;
	}

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
    {
        File file = getItem(position);
        if (file.isDirectory())
        {
            if (file.equals(folder))
            {
                File parent = folder.getParentFile();
                if (parent != null)
                {
                    folder = parent;
                }
                else
                {
                    return;
                }
            }
            else
            {
                folder = file;
            }
            initFiles();
        }
        else
        {
            chooser.onChoose(file.getAbsolutePath());
        }
	}
}
