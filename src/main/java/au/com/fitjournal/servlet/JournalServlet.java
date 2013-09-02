package au.com.fitjournal.servlet;

import au.com.fitjournal.bean.JournalDay;
import au.com.fitjournal.data.entity.JournalEntry;
import au.com.fitjournal.factory.JournalFactory;
import au.com.fitjournal.service.JournalService;
import au.com.fitjournal.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/journal")
public class JournalServlet extends HttpServlet
{
    private JournalService journalService = new JournalService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        List<JournalDay> days = journalService.getJournalDays();
        ServletUtil.writeJson(res, days);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        JournalEntry entry = journalService.save(JournalFactory.fromRequest(req));

        ServletUtil.writeJson(res, entry);
    }
}
