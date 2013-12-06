package newversion.au.com.fitjournal.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "weight_log")
@NamedQueries({
    @NamedQuery(name = "WeightLogEntity.findAll", query = "SELECT wl FROM WeightLogEntity wl ORDER BY wl.date"),
    @NamedQuery(name = "WeightLogEntity.findByDate", query = "SELECT wl FROM WeightLogEntity wl WHERE wl.date = :date"),
    @NamedQuery(name = "WeightLogEntity.deleteAll", query = "DELETE FROM WeightLogEntity wl")
})
public class WeightLogEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private BigDecimal weight;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public BigDecimal getWeight()
    {
        return weight;
    }

    public void setWeight(BigDecimal weight)
    {
        this.weight = weight;
    }
}
