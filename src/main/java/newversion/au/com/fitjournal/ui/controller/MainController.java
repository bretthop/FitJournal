package newversion.au.com.fitjournal.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController
{
    @RequestMapping()
    public String hello()
    {
        return "index";
    }
}
