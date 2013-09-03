package au.com.fitjournal.servlet;

import au.com.fitjournal.bean.JournalPeriod;
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

@WebServlet(urlPatterns = "/journal")
public class JournalServlet extends HttpServlet
{
    private JournalService journalService = new JournalService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        JournalPeriod period = journalService.getJournalPeriod();
        ServletUtil.writeJson(res, period);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        JournalEntry entry = journalService.save(JournalFactory.fromRequest(req));

        ServletUtil.writeJson(res, entry);
    }
}
