package au.com.fitjournal.factory;

import au.com.fitjournal.data.entity.JournalEntry;
import au.com.fitjournal.model.EntryType;
import au.com.fitjournal.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class JournalFactory
{
    public static JournalEntry fromRequest(HttpServletRequest req)
    {
        String id           = req.getParameter("id");
        String name         = req.getParameter("name");
        String kj           = req.getParameter("kj");
        String entryTimeStr = req.getParameter("entryTime");
        String type         = req.getParameter("type");

        JournalEntry entry = new JournalEntry();

        entry.setId(id != null && id.length() > 0 ? Long.parseLong(id) : null);
        entry.setName(name);
        entry.setKj(new BigDecimal(kj));
        entry.setEntryTime(DateUtil.parse(entryTimeStr, DateUtil.FULL_DATE_FORMAT));
        entry.setType(EntryType.valueOf(type));

        return entry;
    }
}
