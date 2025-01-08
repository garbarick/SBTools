package ru.net.serbis.tools.popup;

import android.os.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;
import android.graphics.drawable.*;

public class BrightnessPopup extends PopupWindow
{
    private View parent;
    private SeekBar value;
    private int min = 60;
    private int max = 180;

    public BrightnessPopup(View parent)
    {
        this.parent = parent;
        setContentView(LayoutInflater.from(parent.getContext()).inflate(R.layout.brightness, null));
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setOutsideTouchable(true);
        initValue();
    }

    private void initValue()
    {
        value = UITool.get().findView(getContentView(), R.id.value);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        {
            value.setMin(min);
        }
        value.setMax(max);
        value.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onProgressChanged(SeekBar seek, int progress, boolean byUser)
                {
                    if(progress < min)
                    {
                        progress = min;
                        seek.setProgress(progress);
                    }
                    if (byUser)
                    {
                        Params.BRIGHTNESS.saveValue(progress);
                        ViewTool.get().setColorTransparent(parent, Params.BRIGHTNESS.getValue(), Params.COLOR_FILTER.getValue());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seek)
                {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seek)
                {
                }
            }
        );
    }

    public void show()
    {
        value.setProgress(Params.BRIGHTNESS.getValue());
        showAtLocation(parent, Gravity.CENTER, 0, 0);
    }
}
