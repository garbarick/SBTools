package ru.net.serbis.tools.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.util.*;

public class FilePathAdapter extends ArrayAdapter<String> implements AdapterView.OnItemClickListener
{
    private class StringComparator implements Comparator<String>
    {
        @Override
        public int compare(String p1, String p2)
        {
            return get(p1).compareTo(get(p2));
        }
        
        private String get(String p)
        {
            return p == null ? "" : p;
        }
    }

    private Set<Integer> checked = new HashSet<Integer>();

    public FilePathAdapter(Context context, Set<String> pathes)
    {
        super(context, 0);
        addAll(pathes);
        sort();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.file_path, parent, false);
        }
        String path = getItem(position);

        CheckedTextView nameView = UITool.get().findView(view, R.id.name);
        nameView.setText(new File(path).getName());
        nameView.setChecked(checked.contains(position));

        TextView parhView = UITool.get().findView(view, R.id.path);
        parhView.setText(path);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        setNotifyOnChange(false);
        if (checked.contains(position))
        {
            checked.remove(position);
        }
        else
        {
            checked.add(position);
        }
        notifyDataSetChanged();
    }

    @Override
    public void add(String path)
    {
        if (getPosition(path) > -1)
        {
            return;
        }
        super.add(path);
        sort();
    }

    private void sort()
    {
        checked.clear();
        sort(new StringComparator());
    }

    public Set<String> getPathes()
    {
        Set<String> pathes = new TreeSet<String>();
        for (int position = 0; position < getCount(); position ++)
        {
            pathes.add(getItem(position));
        }
        return pathes;
    }

    public void deleteChecked()
    {
        List<String> removes = new ArrayList<String>();
        for (int position : checked)
        {
            String path = getItem(position);
            removes.add(path);
        }
        checked.clear();
        for (String remove : removes)
        {
            remove(remove);
        }
    }
}
