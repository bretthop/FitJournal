package au.com.fitjournal.factory;

import au.com.fitjournal.data.entity.JournalEntry;
import au.com.fitjournal.model.EntryType;
import au.com.fitjournal.util.DateUtil;
import au.com.fitjournal.util.ObjectUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class JournalFactory
{
    public static JournalEntry fromRequest(HttpServletRequest req)
    {
        String id           = req.getParameter("id");
        String name         = req.getParameter("name");
        String kj           = req.getParameter("kj");
        String protein      = req.getParameter("protein");
        String entryTimeStr = req.getParameter("entryTime");
        String type         = req.getParameter("type");

        JournalEntry entry = new JournalEntry();

        entry.setId(ObjectUtil.isNotEmpty(id) ? Long.parseLong(id) : null);
        entry.setName(name);
        entry.setKj(new BigDecimal(kj));
        entry.setProtein(ObjectUtil.isNotEmpty(protein) ? new BigDecimal(protein) : null);
        entry.setEntryTime(DateUtil.parse(entryTimeStr, DateUtil.FULL_DATE_FORMAT));
        entry.setType(EntryType.valueOf(type));

        return entry;
    }
}
