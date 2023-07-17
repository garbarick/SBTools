package ru.net.serbis.tools.activity;

import android.content.*;
import android.content.res.*;
import android.os.*;
import android.util.*;
import android.widget.*;
import java.io.*;
import org.xmlpull.v1.*;
import ru.net.serbis.tools.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;

import ru.net.serbis.tools.util.Log;

public class ViewXml extends TextActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.RESOURCE))
        {
            Resource resource = (Resource) intent.getSerializableExtra(Constants.RESOURCE);
            edit.setText(readResource(resource));
        }
        Button button = UITool.findView(this, R.id.ok);
        button.setText(android.R.string.copy);
    }

    @Override
    protected void onOk()
    {
        SysTool.setClipBoard(this, R.string.resource_clip_label, edit.getText().toString());
        super.onOk();
    }

    private String readResource(Resource resource)
    {
        XmlResourceParser parser = null;
        try
        {
            Resources resources = getResources();
            parser = resources.getXml(resource.getId());
            switch (Params.XML_FORMAT.getValue(this))
            {
                case XML_SERIALIZER:
                    return convertToStringXmlSerializer(parser);
                case CUSTOM:
                    return convertToStringCustom(parser);
            }
            return "";
        }
        catch (Exception e)
        {
            Log.error(this, e);
            return null;
        }
        finally
        {
            IOTool.close(parser);
        }
    }

    private String convertToStringCustom(XmlResourceParser parser) throws Exception
    {
        StringBuilder result = new StringBuilder();
        int event;
        int tabs = -1;
        String tab = "";
        String attrTab;
        while ((event = parser.next()) != parser.END_DOCUMENT)
        {
            switch (event)
            {
                case parser.START_TAG:
                    tabs ++;
                    tab = getTabs(tabs);
                    attrTab = getTabs(tabs + 1);
                    
                    result
                        .append(tab)
                        .append("<")
                        .append(parser.getName());
                    int attributeCount = parser.getAttributeCount();
                    for (int i = 0; i < attributeCount; i++)
                    {
                        if (tabs == 0 && i == 0)
                        {
                            result
                                .append("\n")
                                .append(attrTab)
                                .append("xmlns:android=\"")
                                .append(parser.getAttributeNamespace(i))
                                .append("\"");
                        }
                        result
                            .append("\n")
                            .append(attrTab)
                            .append("android:")
                            .append(parser.getAttributeName(i))
                            .append("=\"")
                            .append(parser.getAttributeValue(i))
                            .append("\"");
                    }
                    result.append(">\n");
                    break;
                case parser.TEXT:
                    result.append(parser.getText());
                    break;
                case parser.END_TAG:
                    result
                        .append(tab)
                        .append("</")
                        .append(parser.getName())
                        .append(">\n");
                    tabs --;
                    tab = getTabs(tabs);
                    break;
            }
        }
        return result.toString();
    }

    private String getTabs(int tabs)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tabs; i ++)
        {
            result.append("    ");
        }
        return result.toString();
    }

    private String convertToStringXmlSerializer(XmlResourceParser parser) throws Exception
    {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);
        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        int event;
        while ((event = parser.next()) != parser.END_DOCUMENT)
        {
            switch (event)
            {
                case parser.START_TAG:
                    serializer.startTag(parser.getNamespace(), parser.getName());
                    int attributeCount = parser.getAttributeCount();
                    for (int i = 0; i < attributeCount; i++)
                    {
                        serializer.attribute(
                            parser.getAttributeNamespace(i),
                            parser.getAttributeName(i),
                            parser.getAttributeValue(i));
                    }
                    break;
                case parser.TEXT:
                    serializer.text(parser.getText());
                    break;
                case parser.END_TAG:
                    serializer.endTag(parser.getNamespace(), parser.getName());
                    break;
            }
        }
        serializer.endDocument();
        return writer.toString();
    }
}
