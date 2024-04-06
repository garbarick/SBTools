package ru.net.serbis.tools.tool;

import android.widget.*;
import java.io.*;
import java.text.*;
import java.util.*;
import org.json.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.data.param.*;
import ru.net.serbis.tools.dialog.*;
import ru.net.serbis.tools.fragment.*;
import ru.net.serbis.tools.util.*;

public class ExportImport extends Tool
{
    private PopupMenu menu;

    public ExportImport()
    {
        super(
            R.layout.tool_export_import,
            R.id.export_import,
            R.id.export_import_set);
    }

    @Override
    public int getNameId()
    {
        return R.string.export_import;
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.export_import:
                initMenu();
                menu.show();
                break;

            case R.id.export_import_set:
                new ParamsFragment(context, R.string.settings, Params.EXPORT_IMPORT_PARAMS);
                break;

            case R.id.export_params:
                exportParams();
                break;

            case R.id.import_params:
                importParams();
                break;
        }
    }

    private void initMenu()
    {
        if (menu != null)
        {
            return;
        }
        Button button = UITool.get().findView(context, R.id.export_import);
        menu = new PopupMenu(context, button);
        menu.getMenuInflater().inflate(R.menu.export_import, menu.getMenu());
        menu.setOnMenuItemClickListener(this);
    }

    private void exportParams()
    {
        File dir = new File(Params.EXPORT_IMPORT_DIR.getValue());
        dir.mkdirs();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String appName = context.getResources().getString(R.string.app);
        File file = new File(dir, appName + "-" + format.format(new Date()) + Constants.JSON_EXT);
        JSONObject json = new JSONObject();
        for (Param param : Reflection.get().getValues(Params.class, Param.class).values())
        {
            exportParam(json, param);
        }
        IOTool.get().copy(json, file);
        UITool.get().toast(file.getAbsolutePath());
    }

    private void exportParam(JSONObject json, Param param)
    {
        String name = param.getName();
        Object value = param.getValue();
        try
        {
            if (value instanceof Collection)
            {
                JSONArray array = JsonTool.get().toJson((Collection) value);
                json.put(name, array);
            }
            else
            {
                String string = param.typeToString(value);
                json.put(name, string);
            }
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
    }

    private void importParams()
    {
        File dir = new File(Params.EXPORT_IMPORT_DIR.getValue());
        new FileChooser(context, R.string.choose_file, false, true, dir, Constants.JSON_EXT)
        {
            @Override
            public void onChoose(String path)
            {
                JSONObject json = JsonTool.get().parse(new File(path));
                for (Param param : Reflection.get().getValues(Params.class, Param.class).values())
                {
                    importParam(param, json);
                }
                UITool.get().toast(context.getResources().getString(R.string.done));
            }
        };
    }

    private void importParam(Param param, JSONObject json)
    {
        String name = param.getName();
        if (json.has(name))
        {
            try
            {
                Object value = json.get(name);
                if (value instanceof String)
                {
                    param.saveValue(param.stringToType((String)value));
                }
                else if (value instanceof JSONArray)
                {
                    Set<String> set = JsonTool.get().toSet((JSONArray) value);
                    param.saveValue(set);
                }
            }
            catch (Exception e)
            {
                Log.error(this, e);
            }
        }
    }
}
