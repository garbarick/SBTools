package ru.net.serbis.tools.dialog;

import android.app.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.task.*;
import ru.net.serbis.tools.util.*;

public class ActivitiesDialog extends AlertDialog.Builder implements AdapterView.OnItemClickListener, View.OnClickListener, TaskCallback<Collection<ActivityItem>>
{
    private AlertDialog dialog;
    private LinearLayout view;
    private ListView list;
    private ActvitiesAdapter adapter;
    private ProgressBar bar;
    private String title;
    private Button positive;
    private Button negative;

    public ActivitiesDialog(Activity context, Collection<ActivityItem> items)
    {
        super(context);

        view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.list_with_progress, null, false);

        list = UITool.get().findView(view, R.id.list);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter = new ActvitiesAdapter(context, items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        bar = UITool.get().findView(view, R.id.progress);

        title = context.getResources().getString(R.string.activities);
        setTitle(title);
        setView(view);

        setPositiveButton(android.R.string.ok, null);
        setNegativeButton(android.R.string.cancel, null);

        dialog = show();

        positive = dialog.getButton(Dialog.BUTTON_POSITIVE);
        positive.setOnClickListener(this);
        negative = dialog.getButton(Dialog.BUTTON_NEGATIVE);
        negative.setOnClickListener(this);
    }

    @Override
    public void onClick(View button)
    {
        if (UITool.get().isEnabled(view))
        {
            if (adapter.inLevelTwo())
            {
                if (button == negative)
                {
                    onItemClick(null, null, 0, 0);
                }
                else if (button == positive)
                {
                    openActivity();
                }
                return;
            }
            else
            {
                if (button == positive)
                {
                    int selected = list.getCheckedItemPosition();
                    if (selected > -1)
                    {
                        onItemClick(null, null, selected, 0);
                    }
                    return;
                }
            }
            dialog.dismiss();
        }
    }
    
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
    {
        adapter.onItemClick(this, list, position);
        if (adapter.inLevelTwo())
        {
            positive.setText(R.string.open);
            negative.setText(R.string.back);
        }
        else
        {
            positive.setText(android.R.string.ok);
            negative.setText(android.R.string.cancel);
        }
    }
    
    public void loadChildren(PackageItem item)
    {
        UITool.get().disableAll(view);
        adapter.enable(false);
        new PackagesLoader(getContext(), this).execute(item);
    }

    @Override
    public void progress(int progress)
    {
        bar.setProgress(progress);
    }

    @Override
    public void onResult(Collection<ActivityItem> result, TaskError error)
    {
        UITool.get().enableAll(view);
        adapter.enable(true);
        if (error != null)
        {
            UITool.get().toast(getContext(), error);
            return;
        }
        adapter.updateCurrent(this, list, result);
    }
    
    public void setDialogTitle(String title)
    {
        if (title == null)
        {
            dialog.setTitle(this.title);
        }
        else
        {
            dialog.setTitle(title);
        }
    }
    
    private void openActivity()
    {
        int selected = list.getCheckedItemPosition();
        if (selected > 0)
        {
            ActivityItem item = adapter.getItem(selected);
            item.start(getContext());
        }
    }
}
