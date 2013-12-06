package newversion.au.com.fitjournal.service;

import newversion.au.com.fitjournal.data.dao.JournalDao;
import newversion.au.com.fitjournal.data.entity.JournalEntity;
import newversion.au.com.fitjournal.ui.bean.JournalDay;
import newversion.au.com.fitjournal.ui.bean.JournalPeriod;
import newversion.au.com.fitjournal.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class JournalService
{
    @Resource
    private JournalDao journalDao;

    @Resource
    private WeightService weightService;

    public JournalPeriod getFullJournalPeriod()
    {
        List<JournalEntity> allEntries = journalDao.findAll();

        return this.toJournalPeriod(allEntries);
    }

    public JournalPeriod getJournalPeriod(Date startDate, Date endDate)
    {
        List<JournalEntity> allEntries = journalDao.findBetweenDates(startDate, endDate);

        return this.toJournalPeriod(allEntries);
    }

    public JournalEntity getJournalEntity(long id)
    {
        return journalDao.findById(id);
    }

    private JournalPeriod toJournalPeriod(List<JournalEntity> entries)
    {
        Map<Date, JournalDay> days = new TreeMap<Date, JournalDay>();

        // Fill out the hash map with a day for every day between the first date and the last day
        Date firstDate       = entries.get(0).getEntryTime();
        Date lastDate        = entries.get(entries.size() - 1).getEntryTime();
        Date onePastLastDate = DateUtil.addDays(lastDate, 1);

        for (Date d = firstDate; !DateUtil.daysEqual(d, onePastLastDate); d = DateUtil.addDays(d, 1)) {
            Date truncatedDate = DateUtil.truncateToDay(d);

            JournalDay day = new JournalDay();
            day.setDate(truncatedDate);

            weightService.addTargetDailyIntake(day);

            days.put(truncatedDate, day);
        }

        // Loop through the entries and place them in their proper days
        for (JournalEntity entry : entries) {
            Date entryDay = DateUtil.truncateToDay(entry.getEntryTime());

            JournalDay day = days.get(entryDay);

            day.addEntry(entry);
        }

        JournalPeriod period = new JournalPeriod();

        period.setDays(new ArrayList<JournalDay>(days.values()));

        return period;
    }



    public JournalEntity save(JournalEntity entity)
    {
        return journalDao.save(entity);
    }

    public void deleteEntry(long id)
    {
        journalDao.deleteById(id);
    }
}
