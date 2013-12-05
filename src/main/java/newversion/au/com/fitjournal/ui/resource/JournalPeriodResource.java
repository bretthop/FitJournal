package newversion.au.com.fitjournal.ui.resource;

import newversion.au.com.fitjournal.service.JournalService;
import newversion.au.com.fitjournal.ui.bean.JournalPeriod;
import newversion.au.com.fitjournal.util.DateUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping(
    value = "/api/journalPeriod",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class JournalPeriodResource
{
    @Resource
    private JournalService journalService;

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.FULL_DATE_FORMAT, true));
    }

    @RequestMapping
    public @ResponseBody JournalPeriod getCollection(@RequestParam(value = "startDate", required = false) Date startDate, @RequestParam(value = "endDate", required = false) Date endDate)
    {
        // TODO: Test that this is working
        if (startDate != null && endDate != null) {
            return journalService.getJournalPeriod(startDate, endDate);
        }
        else {
            return journalService.getFullJournalPeriod();
        }
    }
}
