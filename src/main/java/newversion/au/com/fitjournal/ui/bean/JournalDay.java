package newversion.au.com.fitjournal.ui.bean;

import newversion.au.com.fitjournal.util.DateUtil;
import newversion.au.com.fitjournal.data.entity.JournalEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JournalDay
{
    private Date date;
    private List<JournalEntity> entries;
    private List<JournalTimeslot> timeslots;
    private BigDecimal targetDailyIntake;

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

    public List<JournalEntity> getEntries()
    {
        return entries;
    }

    public void setEntries(List<JournalEntity> entries)
    {
        this.entries = entries;
    }

    public void addEntry(JournalEntity entry)
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

    public BigDecimal getTargetDailyIntake()
    {
        return targetDailyIntake;
    }

    public void setTargetDailyIntake(BigDecimal targetDailyIntake)
    {
        this.targetDailyIntake = targetDailyIntake;
    }

    public BigDecimal getNetKj()
    {
        BigDecimal result = BigDecimal.ZERO;

        if (this.getEntries() != null) {
            for (JournalEntity entry : this.getEntries()) {
                result = result.add(entry.getKj());
            }
        }

        return result.subtract(this.targetDailyIntake);
    }

    public BigDecimal getTotalProtein()
    {
        BigDecimal result = BigDecimal.ZERO;

        if (this.getEntries() != null) {
            for (JournalEntity entry : this.getEntries()) {
                if (entry.getProtein() != null) {
                    result = result.add(entry.getProtein());
                }
            }
        }

        return result;
    }
}
