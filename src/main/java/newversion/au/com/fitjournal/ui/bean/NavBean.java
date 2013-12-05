package newversion.au.com.fitjournal.ui.bean;

public class NavBean
{
    private UserAccountBean currentUser;
    private String pageName;

    public NavBean(String pageName)
    {
        this.pageName = pageName;
    }

    public UserAccountBean getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(UserAccountBean currentUser)
    {
        this.currentUser = currentUser;
    }

    public String getPageName()
    {
        return pageName;
    }

    public void setPageName(String pageName)
    {
        this.pageName = pageName;
    }

    public String linkState(String pageName)
    {
        return pageName.equals(this.getPageName()) ? "active" : "";
    }

    public boolean getCanSeeSubMenu()
    {
        return pageName.equals("cattleGroups");
    }
}
