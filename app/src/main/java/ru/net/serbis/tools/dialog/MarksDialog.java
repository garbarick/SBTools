package ru.net.serbis.tools.dialog;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.activity.*;
import ru.net.serbis.tools.adapter.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.mark.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.utils.*;

public class MarksDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener, View.OnClickListener, PopupMenu.OnMenuItemClickListener, DialogInterface.OnDismissListener
{
    private ToolsActivity context;
    private ListView list;
    private MarksAdapter adapter;
    private PopupMenu menu;

    public MarksDialog(ToolsActivity context)
    {
        super(context);
        this.context = context;
        setTitle(R.string.marks);
        list = new ListView(context);
        setView(list);

        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter = new MarksAdapter(context);
        list.setAdapter(adapter);

        setNeutralButton(" ", null);
        setNegativeButton(R.string.close, this);
        setOnDismissListener(this);

        show();
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
        ViewTool.get().setSandwitchView(neutral);
        neutral.setOnClickListener(this);

        menu = new PopupMenu(getContext(), neutral);
        menu.getMenuInflater().inflate(R.menu.add_edit_delete, menu.getMenu());
        menu.setOnMenuItemClickListener(this);
    }

    @Override
    public void onClick(DialogInterface dialog, int id)
    {
        switch (id)
        {
            case Dialog.BUTTON_NEUTRAL:
                menu.show();
                break;
        }
    }

    @Override
    public void onClick(View view)
    {
        onClick(null, view.getId());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add:
                add();
                return true;
            case R.id.edit:
                edit();
                return true;
            case R.id.delete:
                delete();
                return true;
        }
        return false;
    }

    private void add()
    {
        Mark mark = new Mark();
        edit(mark, true);
    }

    private void edit()
    {
        int checked = list.getCheckedItemPosition();
        if (checked == -1)
        {
            return;
        }
        Mark mark = adapter.getItem(checked);
        edit(mark, false);
    }

    private void edit(final Mark mark, final boolean add)
    {
        final Map<Integer, Param> params = new LinkedHashMap<Integer, Param>();
        params.put(R.string.name, new EditTextParam(Strings.get().get(R.string.name), mark.getName(), false));
        params.put(R.string.last_date, new EditDateParam(Strings.get().get(R.string.last_date), mark.getLastDate(), false));
        params.put(R.string.last_petiod, new EditNumberParam(Strings.get().get(R.string.last_petiod), mark.getLastPeriod(), false));
        params.put(R.string.period, new EditNumberParam(Strings.get().get(R.string.period), mark.getPeriod(), false));
        params.put(R.string.period_units, new UnitParam(Strings.get().get(R.string.period_units), mark.getPeriodUnit(), false));

        Param[] paramsArray = params.values().toArray(new Param[params.size()]);

        new ParamsDialog(context, R.string.edit, paramsArray, true, false)
        {
            @Override
            public void ok(ParamsAdapter adapter)
            {
                update(adapter, mark, add, params);
            }
        }.show();
    }

    private void delete()
    {
        int checked = list.getCheckedItemPosition();
        if (checked == -1)
        {
            return;
        }
        adapter.remove(adapter.getItem(checked));
    }

    private void update(ParamsAdapter paramsAdapter, Mark mark, boolean add, Map<Integer, Param> params)
    {
        mark.setName((String) paramsAdapter.getValue(params.get(R.string.name)));
        mark.setLastDate((Date) paramsAdapter.getValue(params.get(R.string.last_date)));
        mark.setLastPeriod((Integer) paramsAdapter.getValue(params.get(R.string.last_petiod)));
        mark.setPeriod((Integer) paramsAdapter.getValue(params.get(R.string.period)));
        mark.setPeriodUnit((Unit) paramsAdapter.getValue(params.get(R.string.period_units)));
        mark.setNextDate();
        if (add)
        {
            adapter.add(mark);
        }
        adapter.sort();
        adapter.notifyDataSetChanged();
        adapter.save();
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        context.closeTool();
    }
}
