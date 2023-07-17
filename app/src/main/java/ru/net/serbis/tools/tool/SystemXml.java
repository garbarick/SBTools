package ru.net.serbis.tools.tool;

import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.dialog.*;

public class SystemXml extends Tool
{
    public SystemXml()
    {
        super(
            R.layout.tool_sys_xml,
            R.id.sys_xml,
            R.id.sys_xml_set);
    }

    @Override
    public void onClick(int id)
    {
        switch (id)
        {
            case R.id.sys_xml:
                new XmlDialog(context);
                break;

            case R.id.sys_xml_set:
                new ParamsDialog(context, R.string.sys_xml_set, Params.SYS_XML_PARAMS);
                break;
        }
    }
}
