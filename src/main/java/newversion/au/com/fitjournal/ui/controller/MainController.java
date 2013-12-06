package newversion.au.com.fitjournal.ui.controller;

import newversion.au.com.fitjournal.ui.bean.NavBean;
import newversion.au.com.fitjournal.ui.bean.UserAccountBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController
{
    @RequestMapping()
    public String index(ModelMap model)
    {
        return this.weight(model);
    }

    @RequestMapping("/weight")
    public String weight(ModelMap model)
    {
        NavBean navBean = new NavBean("weight");
        navBean.setCurrentUser(this.createDummyUser());

        model.addAttribute("navBean", navBean);

        return "weight";
    }

    @RequestMapping("/energy")
    public String energy(ModelMap model)
    {
        NavBean navBean = new NavBean("energy");
        navBean.setCurrentUser(this.createDummyUser());

        model.addAttribute("navBean", navBean);

        return "energy";
    }

    private UserAccountBean createDummyUser()
    {
        UserAccountBean dummy = new UserAccountBean();

        dummy.setEmail("brett.hopper@hotmail.com");
        dummy.setFirstName("Brett");
        dummy.setLastName("Hopper");
        dummy.setUsername("bretthop");
        dummy.setPassword("password");

        return dummy;
    }
}
