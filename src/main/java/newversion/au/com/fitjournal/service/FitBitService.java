package newversion.au.com.fitjournal.service;

import com.fitbit.api.common.model.body.WeightLog;
import newversion.au.com.fitjournal.data.dao.WeightLogDao;
import newversion.au.com.fitjournal.data.entity.WeightLogEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class FitBitService
{
    @Resource
    WeightLogDao weightLogDao;

    public void importFitBitWeightLog(List<WeightLog> weightLog)
    {
        weightLogDao.deleteAll();

        for (WeightLog weight : weightLog) {
            WeightLogEntity weightLogEntity = new WeightLogEntity();
            weightLogEntity.setDate(weight.getDate().toDateMidnight().toDate());
            weightLogEntity.setWeight(new BigDecimal(weight.getWeight()));

            weightLogDao.save(weightLogEntity);
        }
    }
}
