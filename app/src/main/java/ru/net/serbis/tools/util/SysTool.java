package ru.net.serbis.tools.util;

import android.content.*;

public class SysTool
{
    public static <T> T getService(Context context, String name)
    {
        return (T) context.getSystemService(name);
    }

    public static void setClipBoard(Context context, int labelId, String text)
    {
        ClipboardManager clipboard = getService(context, Context.CLIPBOARD_SERVICE);
        String label = context.getResources().getString(labelId);
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
    }
}
