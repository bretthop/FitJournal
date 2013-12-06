package newversion.au.com.fitjournal.ui.resource;

import newversion.au.com.fitjournal.data.entity.WeightEntity;
import newversion.au.com.fitjournal.data.entity.WeightLogEntity;
import newversion.au.com.fitjournal.service.FitBitService;
import newversion.au.com.fitjournal.service.WeightService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping(
        value = "/api/weightLog",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class WeightLogResource
{
    @Resource
    private WeightService weightService;

    @Resource
    private FitBitService fitBitService;

    @RequestMapping()
    public @ResponseBody WeightLogEntity getActualWeight()
    {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        return weightService.getFitBitWeight(now.getTime());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody boolean deleteWeightLog()
    {
        fitBitService.deleteFitBitData();

        return true;
    }
}
