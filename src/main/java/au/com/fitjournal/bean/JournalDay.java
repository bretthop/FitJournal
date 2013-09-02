package au.com.fitjournal.bean;

import au.com.fitjournal.data.entity.JournalEntry;
import au.com.fitjournal.util.DateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JournalDay
{
    private Date date;
    private List<JournalEntry> entries;
    private List<JournalTimeslot> timeslots;

    public JournalDay()
    {
        this.entries = new ArrayList<>();
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getTitle()
    {
        return DateUtil.DAY_TEXT_FORMAT.format(this.getDate());
    }

    public List<JournalEntry> getEntries()
    {
        return entries;
    }

    public void setEntries(List<JournalEntry> entries)
    {
        this.entries = entries;
    }

    public void addEntry(JournalEntry entry)
    {
        this.getEntries().add(entry);
    }

    public List<JournalTimeslot> getTimeslots()
    {
        return timeslots;
    }

    public void setTimeslots(List<JournalTimeslot> timeslots)
    {
        this.timeslots = timeslots;
    }

    public BigDecimal getNetKj()
    {
        BigDecimal STANDARD_DAILY_KJ = new BigDecimal("8700");

        BigDecimal result = BigDecimal.ZERO;

        if (this.getEntries() != null) {
            for (JournalEntry entry : this.getEntries()) {
                result = result.add(entry.getKj());
            }
        }

        return result.subtract(STANDARD_DAILY_KJ);
    }
}
