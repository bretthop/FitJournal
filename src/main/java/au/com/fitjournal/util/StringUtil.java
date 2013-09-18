package au.com.fitjournal.util;

public class StringUtil
{
    public static String toStartCase(String s)
    {
        String result = s;

        if (s != null && s.length() > 0) {
            result = s.toUpperCase().substring(0, 1);
            result += s.toLowerCase().substring(1);
        }

        return result;
    }
}
