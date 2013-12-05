package newversion.au.com.fitjournal.ui.resource;

import newversion.au.com.fitjournal.data.entity.JournalEntity;
import newversion.au.com.fitjournal.service.JournalService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(
        value = "/api/journal",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class JournalResource
{
    @Resource
    private JournalService journalService;

    @RequestMapping("/{id}")
    public @ResponseBody JournalEntity getInstance(@PathVariable("id") long id)
    {
        return journalService.getJournalEntity(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody JournalEntity postInstance(@RequestBody JournalEntity entity)
    {
        return journalService.save(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteInstance(@PathVariable("id") long id)
    {
        journalService.deleteEntry(id);
    }
}
