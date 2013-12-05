package newversion.au.com.fitjournal.ui.resource;

import newversion.au.com.fitjournal.data.entity.WeightEntity;
import newversion.au.com.fitjournal.service.WeightService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(
        value = "/api/weight",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class WeightResource
{
    @Resource
    private WeightService weightService;

    @RequestMapping()
    public @ResponseBody WeightEntity getTargetWeight()
    {
        return weightService.getTargetWeight();
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WeightEntity postTargetWeight(@RequestBody WeightEntity entity)
    {
        return weightService.save(entity);
    }
}
