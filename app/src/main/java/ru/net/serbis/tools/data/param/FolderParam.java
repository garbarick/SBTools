package ru.net.serbis.tools.data.param;

import android.content.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.dialog.*;

public class FolderParam extends TextParam<TextView>
{
    public FolderParam(int nameId, String defaultValue)
    {
        super(nameId, defaultValue);
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.param_text;
    }

    @Override
    public void initViewValue(View parent, final Context context)
    {
        final TextView view = getViewValue(parent);
        view.setText(getValue(context));
        view.setOnClickListener(
            new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    new FileChooser(context, R.string.choose_dir, true)
                    {
                        @Override
                        public void onChoose(String path)
                        {
                            view.setText(path);
                        }
                    };
                }
            }
        );
    }

    @Override
    protected String getValue(TextView view)
    {
        return view.getText().toString();
    }
}
