package newversion.au.com.fitjournal.data.entity;

import newversion.au.com.fitjournal.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "journal_entry")
@NamedQueries({
    @NamedQuery(name = "JournalEntity.findAll", query = "SELECT j FROM JournalEntity j ORDER BY j.entryTime"),
    @NamedQuery(name = "JournalEntity.findBetweenDates", query = "SELECT j FROM JournalEntity j WHERE j.entryTime >= :start AND j.entryTime <= :end ORDER BY j.entryTime"),
    @NamedQuery(name = "JournalEntity.findById", query = "SELECT j FROM JournalEntity j WHERE j.id = :id"),
    @NamedQuery(name = "JournalEntity.deleteById", query = "DELETE FROM JournalEntity j WHERE j.id = :id")
})
public class JournalEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal kj;
    private BigDecimal protein;

    @Transient
    private String entryTimeStr;

    private Date entryTime;
    private String type;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

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

    public BigDecimal getProtein()
    {
        return protein;
    }

    public void setProtein(BigDecimal protein)
    {
        this.protein = protein;
    }

    public Date getEntryTime()
    {
        return entryTime;
    }

    public void setEntryTime(Date entryTime)
    {
        this.entryTime = entryTime;
    }

    public String getEntryTimeStr()
    {
        return entryTimeStr;
    }

    public void setEntryTimeStr(String entryTimeStr)
    {
        this.entryTimeStr = entryTimeStr;
    }

    public String getFormattedTime()
    {
        return DateUtil.AM_PM_TIME_FORMAT.format(this.getEntryTime());
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
