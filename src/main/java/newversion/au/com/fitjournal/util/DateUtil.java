package newversion.au.com.fitjournal.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
    public static final DateFormat FULL_DATE_FORMAT     = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat AM_PM_TIME_FORMAT    = new SimpleDateFormat("h:mm a");
    public static final DateFormat DAY_TEXT_FORMAT      = new SimpleDateFormat("EE");

    public static Calendar toCal(Date d)
    {
        Calendar c = Calendar.getInstance();

        c.setTime(d);

        return c;
    }

    public static String format(Date d, DateFormat f)
    {
        return f.format(d);
    }

    public static Date parse(String s, DateFormat f)
    {
        Date result = null;

        try {
            result = f.parse(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Date addDays(Date d, int n)
    {
        Calendar c = DateUtil.toCal(d);

        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + n);

        return c.getTime();
    }

    public static Date truncateToDay(Date d)
    {
        Calendar c = DateUtil.toCal(d);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static boolean daysEqual(Date d1, Date d2)
    {
        Calendar c1 = DateUtil.toCal(d1);
        Calendar c2 = DateUtil.toCal(d2);

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
                c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }
}