package ru.net.serbis.tools.data.mark;

import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import org.json.*;
import ru.net.serbis.tools.util.*;

public class Mark
{
    private String name;
    private Date lastDate;
    private Date nextDate;
    private int lastPeriod;
    private int period;

    public Mark(String name, Date lastDate, int period)
    {
        this.name = name;
        this.lastDate = lastDate;
        this.period = period;
        setNextDate();
    }
    
    public Mark(String name)
    {
        this(name, new Date(), 0);
    }

    public Mark()
    {
        this("");
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setLastDate(Date lastDate)
    {
        this.lastDate = lastDate;
    }

    public void setLastDate(String lastDate)
    {
        this.lastDate = getDate(lastDate);
    }

    public Date getLastDate()
    {
        return lastDate;
    }

    public String getLastDateString()
    {
        return getFormat().format(lastDate);
    }

    public void setPeriod(int period)
    {
        this.period = period;
    }

    public int getPeriod()
    {
        return period;
    }

    public String getPeriodString()
    {
        return "" + period;
    }

    public void setLastPeriod(int lastPeriod)
    {
        this.lastPeriod = lastPeriod;
    }

    public int getLastPeriod()
    {
        return lastPeriod;
    }

    public String getLastPeriodString()
    {
        return "" + lastPeriod;
    }

    public int getCurrentPeriod()
    {
        long diff = new Date().getTime() - lastDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String getCurrentPeriodString()
    {
        return "" + getCurrentPeriod();
    }

    private SimpleDateFormat getFormat()
    {
        return new SimpleDateFormat("yyyy.MM.dd");
    }

    public void setNextDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDate);
        calendar.add(Calendar.DATE, period);
        nextDate = calendar.getTime();
    }

    public String getNextDateString()
    {
        return getFormat().format(nextDate);
    }

    public Date getDate(String date)
    {
        try
        {
            return getFormat().parse(date);
        }
        catch (Exception e)
        {
            return new Date();
        }
    }

    public void update()
    {
        setLastPeriod(getCurrentPeriod());
        setLastDate(new Date());
        setNextDate();
    }

    public boolean isExpired()
    {
        return getCurrentPeriod() > getPeriod();
    }

    public boolean isWarning()
    {
        return getCurrentPeriod() == getPeriod();
    }

    public JSONObject toJson()
    {
        JSONObject result = new JSONObject();
        try
        {
            result.put("n", name);
            result.put("ld", getLastDateString());
            result.put("p", period);
            result.put("lp", lastPeriod);
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
        return result;
    }

    public void parse(JSONObject result)
    {
        try
        {
            setName(result.getString("n"));
            setLastDate(result.getString("ld"));
            setLastPeriod(result.getInt("lp"));
            setPeriod(result.getInt("p"));
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
    }
}
