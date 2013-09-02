package au.com.fitjournal.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtil
{
    public static void writeJson(HttpServletResponse res, Object o) throws IOException
    {
        res.setHeader("Content-Type", "application/json");
        res.getWriter().write(JsonUtil.serialise(o));
    }
}
