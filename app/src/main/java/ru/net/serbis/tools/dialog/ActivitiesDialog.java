package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.res.*;
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
    private ListView applications;
    private ListView activities;
    private ProgressBar bar;
    private Button positive;
    private Button negative;
    private boolean itemsReady;
    private int position;
    private boolean horizontal;

    public ActivitiesDialog(Activity context)
    {
        super(context);

        int layout = R.layout.activities_portrait;
        if (Configuration.ORIENTATION_LANDSCAPE == context.getResources().getConfiguration().orientation)
        {
            layout = R.layout.activities_landscape;
            horizontal = true;
        }
        view = (LinearLayout) LayoutInflater.from(context).inflate(layout, null, false);

        applications = UITool.get().findView(view, R.id.applications);
        applications.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        applications.setAdapter(new ActvitiesAdapter(context));
        applications.setOnItemClickListener(this);

        activities = UITool.get().findView(view, R.id.activities);
        activities.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        activities.setAdapter(new ActvitiesAdapter(context));

        bar = UITool.get().findView(view, R.id.progress);

        setTitle(R.string.activities);
        setView(view);

        setPositiveButton(android.R.string.ok, null);
        setNegativeButton(android.R.string.cancel, null);
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
        initItems();
        return dialog;
    }

    public void initButtons()
    {
        positive = dialog.getButton(Dialog.BUTTON_POSITIVE);
        positive.setOnClickListener(this);
        negative = dialog.getButton(Dialog.BUTTON_NEGATIVE);
        negative.setOnClickListener(this);
        showApplications();
    }

    @Override
    public void onClick(View button)
    {
        if (!UITool.get().isEnabled(view))
        {
            return;
        }
        switch (getButtonTag(button))
        {
            case android.R.string.ok:
                initActivities();
                break;

            case R.string.open:
                openActivity();
                break;

            case R.string.back:
                showApplications();
                break;

            default:
                dialog.dismiss();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> listView, View view, int position, long id)
    {
        if (listView == applications)
        {
            showActivities();
            PackageItem item = (PackageItem) getAdapter(applications).getItem(position);
            initActivities(item);
        }
    }

    public void initItems()
    {
        if (itemsReady)
        {
            return;
        }
        UITool.get().disableAll(view);
        getAdapter(applications).enable(false);
        new PackagesLoader(getContext(), this).execute();
    }

    private ActvitiesAdapter getAdapter(ListView list)
    {
        return (ActvitiesAdapter) list.getAdapter();
    }

    private void initActivities()
    {
        int selected = applications.getCheckedItemPosition();
        if (selected > -1)
        {
            onItemClick(applications, null, selected, 0);
        }
    }

    public void initActivities(PackageItem item)
    {
        UITool.get().disableAll(view);
        getAdapter(activities).enable(false);
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
        if (error != null)
        {
            UITool.get().toast(getContext(), error);
            return;
        }
        if (itemsReady)
        {
            getAdapter(activities).enable(true);
            getAdapter(activities).initItems(result);
        }
        else
        {
            getAdapter(applications).enable(true);
            getAdapter(applications).initItems(result);
            applications.setSelection(position);
            itemsReady = true;
        }
    }

    private void openActivity()
    {
        int selected = activities.getCheckedItemPosition();
        if (selected > -1)
        {
            ActivityItem item = getAdapter(activities).getItem(selected);
            item.start(getContext());
            showApplications();
        }
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public int getPosition()
    {
        return applications.getFirstVisiblePosition();
    }

    private void showApplications()
    {
        if (horizontal)
        {
            UITool.get().enable(applications);
            getAdapter(applications).enable(true);
            UITool.get().disable(activities);
            getAdapter(activities).enable(false);
        }
        else
        {
            applications.setVisibility(View.VISIBLE);
            activities.setVisibility(View.GONE);
        }
        setButtonTag(positive, android.R.string.ok);
        setButtonTag(negative, android.R.string.cancel);
    }

    private void showActivities()
    {
        getAdapter(activities).clear();
        if (horizontal)
        {
            UITool.get().disable(applications);
            getAdapter(applications).enable(false);
            UITool.get().enable(activities);
            getAdapter(activities).enable(true);
        }
        else
        {
            applications.setVisibility(View.GONE);
            activities.setVisibility(View.VISIBLE);
        }
        setButtonTag(positive, R.string.open);
        setButtonTag(negative, R.string.back);
    }
    
    private void setButtonTag(Button button, int id)
    {
        button.setText(id);
        button.setTag(id);
    }

    private int getButtonTag(View button)
    {
        Object result = button.getTag();
        if (result instanceof Integer)
        {
            return (Integer) result;
        }
        return -1;
    }
}
