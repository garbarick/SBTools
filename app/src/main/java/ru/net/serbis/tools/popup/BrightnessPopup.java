package ru.net.serbis.tools.popup;

import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;
import ru.net.serbis.utils.*;

import ru.net.serbis.tools.R;

public class BrightnessPopup extends PopupWindow
{
    private View parent;
    private SeekBar value;

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
        value.setMax(100);
        value.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onProgressChanged(SeekBar seek, int progress, boolean byUser)
                {
                    if (byUser)
                    {
                        Params.BRIGHTNESS.saveValue(
                            Params.BRIGHTNESS.fromProgress(progress));
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
        int progress = Params.BRIGHTNESS.toProgress(
            Params.BRIGHTNESS.getValue());
        value.setProgress(progress);
        showAtLocation(parent, Gravity.CENTER, 0, 0);
    }
}
