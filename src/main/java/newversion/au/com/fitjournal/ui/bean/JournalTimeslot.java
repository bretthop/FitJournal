package newversion.au.com.fitjournal.ui.bean;

import newversion.au.com.fitjournal.data.entity.JournalEntity;

import java.util.Date;
import java.util.List;

public class JournalTimeslot
{
    private Date timeslotTime;
    private List<JournalEntity> entries;

    public Date getTimeslotTime()
    {
        return timeslotTime;
    }

    public void setTimeslotTime(Date timeslotTime)
    {
        this.timeslotTime = timeslotTime;
    }

    public List<JournalEntity> getEntries()
    {
        return entries;
    }

    public void setEntries(List<JournalEntity> entries)
    {
        this.entries = entries;
    }
}