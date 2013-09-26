package au.com.fitjournal.util;

public class ObjectUtil
{
    public static boolean isEmpty(Object o)
    {
        if (o == null) {
            return true;
        }

        if (o instanceof String && ((String) o).length() <= 0) {
            return true;
        }

        return false;
    }

    public static boolean isNotEmpty(Object o)
    {
        return !ObjectUtil.isEmpty(o);
    }
}
