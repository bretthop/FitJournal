package au.com.fitjournal.service;

import au.com.fitjournal.bean.JournalDay;
import au.com.fitjournal.data.dao.JournalEntryDao;
import au.com.fitjournal.data.entity.JournalEntry;
import au.com.fitjournal.util.DateUtil;

import java.util.*;

public class JournalService
{
    private JournalEntryDao journalEntryDao = new JournalEntryDao();

    public List<JournalDay> getJournalDays()
    {
        List<JournalEntry> allEntries = journalEntryDao.findAll();

        Map<Date, JournalDay> days = new TreeMap<>();

        // Fill out the hash map with a day for every day between the first date and the last day
        Date firstDate       = allEntries.get(0).getEntryTime();
        Date lastDate        = allEntries.get(allEntries.size() - 1).getEntryTime();
        Date onePastLastDate = DateUtil.addDays(lastDate, 1);

        for (Date d = firstDate; !DateUtil.daysEqual(d, onePastLastDate); d = DateUtil.addDays(d, 1)) {
            Date truncatedDate = DateUtil.truncateToDay(d);

            JournalDay day = new JournalDay();
            day.setDate(truncatedDate);

            days.put(truncatedDate, day);
        }

        // Loop through the entries and place them in their proper days
        for (JournalEntry entry : allEntries) {
            Date entryDay = DateUtil.truncateToDay(entry.getEntryTime());

            JournalDay day = days.get(entryDay);

            day.addEntry(entry);
        }

        return new ArrayList<>(days.values());
    }

    public JournalEntry save(JournalEntry entry)
    {
        return journalEntryDao.save(entry);
    }
}
