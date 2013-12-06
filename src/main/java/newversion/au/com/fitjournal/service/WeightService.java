package newversion.au.com.fitjournal.service;

import newversion.au.com.fitjournal.data.dao.WeightDao;
import newversion.au.com.fitjournal.data.dao.WeightLogDao;
import newversion.au.com.fitjournal.data.entity.WeightEntity;
import newversion.au.com.fitjournal.data.entity.WeightLogEntity;
import newversion.au.com.fitjournal.ui.bean.FitBitWeightEntity;
import newversion.au.com.fitjournal.ui.bean.JournalDay;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class WeightService
{
    @Resource
    WeightDao weightDao;

    @Resource
    WeightLogDao weightLogDao;

    public WeightEntity getTargetWeight()
    {
        List<WeightEntity> targetWeights = weightDao.findAll();

        return targetWeights.size() > 0 ? targetWeights.get(0) : null;
    }

    public WeightEntity save(WeightEntity entity)
    {
        weightDao.deleteAll();

        return weightDao.save(entity);
    }

    public void addTargetDailyIntake(JournalDay day)
    {
        BigDecimal targetDailyIntake = this.getDailyIntake(day.getDate());

        day.setTargetDailyIntake(targetDailyIntake);
    }

    public BigDecimal getDailyIntake(Date date)
    {
        BigDecimal dailyIntake = new BigDecimal("8700");

        WeightEntity targetWeight = this.getTargetWeight();
        WeightLogEntity currentWeight = this.getFitBitWeight(date);

        if (targetWeight != null && currentWeight != null) {
            BigDecimal difference = targetWeight.getTarget().subtract(currentWeight.getWeight()).setScale(1, BigDecimal.ROUND_HALF_UP);

            dailyIntake = dailyIntake.add(difference.multiply(new BigDecimal("300")));
        }

        return dailyIntake;
    }

    public WeightLogEntity getFitBitWeight(Date date)
    {
        return weightLogDao.findByDate(date);
    }
}
