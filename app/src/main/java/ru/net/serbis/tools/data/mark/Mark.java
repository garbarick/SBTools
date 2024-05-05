package ru.net.serbis.tools.data.mark;

import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import org.json.*;
import ru.net.serbis.tools.data.*;
import ru.net.serbis.tools.util.*;
import android.text.format.*;

public class Mark
{
    private String name = "";
    private Date lastDate = currentDate();
    private Date nextDate;
    private int lastPeriod;
    private int period;
    private Unit periodUnit = Unit.DAYS;

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

    public void setLastPeriod(int lastPeriod)
    {
        this.lastPeriod = lastPeriod;
    }

    public int getLastPeriod()
    {
        return lastPeriod;
    }

    public int getCurrentPeriod()
    {
        long diff = currentDate().getTime() - lastDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    private SimpleDateFormat getFormat()
    {
        return new SimpleDateFormat("yyyy.MM.dd");
    }

    public void setNextDate()
    {
        nextDate = periodUnit.getNext(lastDate, period);
    }

    public void setPeriodUnit(Unit periodUnit)
    {
        this.periodUnit = periodUnit;
    }

    public void setPeriodUnit(String periodUnit)
    {
        this.periodUnit = Unit.get(periodUnit);
    }

    public Unit getPeriodUnit()
    {
        return periodUnit;
    }

    public Date getNextDate()
    {
        return nextDate;
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
        setLastDate(currentDate());
        setNextDate();
    }

    public boolean isExpired()
    {
        return currentDate().after(nextDate);
    }

    public boolean isWarning()
    {
        return currentDate().equals(nextDate);
    }
    
    private Date currentDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public JSONObject toJson()
    {
        JSONObject result = new JSONObject();
        try
        {
            result.put("n", name);
            result.put("ld", getLastDateString());
            result.put("lp", lastPeriod);
            result.put("p", period);
            result.put("pu", periodUnit);
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
        return result;
    }

    public void parse(JSONObject json)
    {
        try
        {
            if (json.has("n"))
            {
                setName(json.getString("n"));
            }
            if (json.has("ld"))
            {
                setLastDate(json.getString("ld"));
            }
            if (json.has("lp"))
            {
                setLastPeriod(json.getInt("lp"));
            }
            if (json.has("p"))
            {
                setPeriod(json.getInt("p"));
            }
            if (json.has("pu"))
            {
                setPeriodUnit(json.getString("pu"));
            }
            setNextDate();
        }
        catch (Exception e)
        {
            Log.error(this, e);
        }
    }
}
