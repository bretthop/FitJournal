package newversion.au.com.fitjournal.ui.bean;

import java.math.BigDecimal;
import java.util.List;

public class JournalPeriod
{
    List<JournalDay> days;

    public List<JournalDay> getDays()
    {
        return days;
    }

    public void setDays(List<JournalDay> days)
    {
        this.days = days;
    }

    public BigDecimal getNetKj()
    {
        BigDecimal result = BigDecimal.ZERO;

        for (JournalDay day : this.getDays()) {
            result = result.add(day.getNetKj());
        }

        return result;
    }

    public BigDecimal getTotalProtein()
    {
        BigDecimal result = BigDecimal.ZERO;

        for (JournalDay day : this.getDays()) {
            result = result.add(day.getTotalProtein());
        }

        return result;
    }
}