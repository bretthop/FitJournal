package au.com.fitjournal.data.entity;

import au.com.fitjournal.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class JournalEntry extends BaseEntity
{
    private String name;
    private BigDecimal kj;
    private Date entryTime;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getKj()
    {
        return kj;
    }

    public void setKj(BigDecimal kj)
    {
        this.kj = kj;
    }

    public Date getEntryTime()
    {
        return entryTime;
    }

    public void setEntryTime(Date entryTime)
    {
        this.entryTime = entryTime;
    }

    public String getFormattedTime()
    {
        return DateUtil.AM_PM_TIME_FORMAT.format(this.getEntryTime());
    }
}
