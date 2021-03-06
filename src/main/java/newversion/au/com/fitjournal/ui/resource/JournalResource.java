package newversion.au.com.fitjournal.ui.resource;

import newversion.au.com.fitjournal.data.entity.JournalEntity;
import newversion.au.com.fitjournal.service.JournalService;
import newversion.au.com.fitjournal.util.DateUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping(
        value = "/api/journal",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class JournalResource
{
    @Resource
    private JournalService journalService;

    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.FULL_DATE_FORMAT, true));
    }

    @RequestMapping("/{id}")
    public @ResponseBody JournalEntity getInstance(@PathVariable("id") long id)
    {
        return journalService.getJournalEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody JournalEntity postInstance(@RequestBody JournalEntity entity)
    {
        // TODO: Remove this disgusting hack
        try {
            entity.setEntryTime(DateUtil.FULL_DATE_FORMAT.parse(entity.getEntryTimeStr()));
        }
        catch (Exception e) {
            // Suppress
        }

        return journalService.save(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody boolean deleteInstance(@PathVariable("id") long id)
    {
        journalService.deleteEntry(id);

        return true;
    }
}
