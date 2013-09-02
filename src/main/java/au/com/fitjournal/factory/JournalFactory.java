package au.com.fitjournal.factory;

import au.com.fitjournal.data.entity.JournalEntry;
import au.com.fitjournal.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class JournalFactory
{
    public static JournalEntry fromRequest(HttpServletRequest req)
    {
        String name         = req.getParameter("name");
        String kj           = req.getParameter("kj");
        String entryTimeStr = req.getParameter("entryTime");

        JournalEntry entry = new JournalEntry();

        entry.setName(name);
        entry.setKj(new BigDecimal(kj));
        entry.setEntryTime(DateUtil.parse(entryTimeStr, DateUtil.FULL_DATE_FORMAT));

        return entry;
    }
}
