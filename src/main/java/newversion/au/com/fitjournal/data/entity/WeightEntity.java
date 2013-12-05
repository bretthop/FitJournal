package newversion.au.com.fitjournal.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "weight")
@NamedQueries({
    @NamedQuery(name = "WeightEntity.findAll", query = "SELECT w FROM WeightEntity w"),
    @NamedQuery(name = "WeightEntity.deleteAll", query = "DELETE FROM WeightEntity w")
})
public class WeightEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal target;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public BigDecimal getTarget()
    {
        return target;
    }

    public void setTarget(BigDecimal target)
    {
        this.target = target;
    }
}
