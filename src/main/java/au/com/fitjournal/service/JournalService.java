package au.com.fitjournal.service;

import au.com.fitjournal.bean.JournalDay;
import au.com.fitjournal.bean.JournalPeriod;
import au.com.fitjournal.data.dao.JournalEntryDao;
import au.com.fitjournal.data.entity.JournalEntry;
import au.com.fitjournal.util.DateUtil;

import java.util.*;

public class JournalService
{
    private JournalEntryDao journalEntryDao = new JournalEntryDao();

    public JournalPeriod getFullJournalPeriod()
    {
        List<JournalEntry> allEntries = journalEntryDao.findAll();

        return this.toJournalPeriod(allEntries);
    }

    public JournalPeriod getJournalPeriod(Date startDate, Date endDate)
    {
        List<JournalEntry> allEntries = journalEntryDao.findBetweenDates(startDate, endDate);

        return this.toJournalPeriod(allEntries);
    }

    private JournalPeriod toJournalPeriod(List<JournalEntry> entries)
    {
        Map<Date, JournalDay> days = new TreeMap<>();

        // Fill out the hash map with a day for every day between the first date and the last day
        Date firstDate       = entries.get(0).getEntryTime();
        Date lastDate        = entries.get(entries.size() - 1).getEntryTime();
        Date onePastLastDate = DateUtil.addDays(lastDate, 1);

        for (Date d = firstDate; !DateUtil.daysEqual(d, onePastLastDate); d = DateUtil.addDays(d, 1)) {
            Date truncatedDate = DateUtil.truncateToDay(d);

            JournalDay day = new JournalDay();
            day.setDate(truncatedDate);

            days.put(truncatedDate, day);
        }

        // Loop through the entries and place them in their proper days
        for (JournalEntry entry : entries) {
            Date entryDay = DateUtil.truncateToDay(entry.getEntryTime());

            JournalDay day = days.get(entryDay);

            day.addEntry(entry);
        }

        JournalPeriod period = new JournalPeriod();

        period.setDays(new ArrayList<>(days.values()));

        return period;
    }

    public JournalEntry save(JournalEntry entry)
    {
        JournalEntry result;

        if (entry.getId() != null && entry.getId() > 0) {
            result = journalEntryDao.update(entry);
        }
        else {
            result = journalEntryDao.insert(entry);
        }

        return result;
    }

    public JournalEntry getJournalEntry(long id)
    {
        return journalEntryDao.findById(id);
    }
}
