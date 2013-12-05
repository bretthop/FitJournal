package newversion.au.com.fitjournal.ui.bean;

import au.com.fitjournal.data.entity.JournalEntry;

import java.util.Date;
import java.util.List;

public class JournalTimeslot
{
    private Date timeslotTime;
    private List<JournalEntry> entries;

    public Date getTimeslotTime()
    {
        return timeslotTime;
    }

    public void setTimeslotTime(Date timeslotTime)
    {
        this.timeslotTime = timeslotTime;
    }

    public List<JournalEntry> getEntries()
    {
        return entries;
    }

    public void setEntries(List<JournalEntry> entries)
    {
        this.entries = entries;
    }
}