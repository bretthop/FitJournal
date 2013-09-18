package au.com.fitjournal.servlet;

import au.com.fitjournal.bean.JournalPeriod;
import au.com.fitjournal.data.entity.JournalEntry;
import au.com.fitjournal.factory.JournalFactory;
import au.com.fitjournal.service.JournalService;
import au.com.fitjournal.util.DateUtil;
import au.com.fitjournal.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/journal")
public class JournalServlet extends HttpServlet
{
    private JournalService journalService = new JournalService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        JournalPeriod result;

        String startDateStr = req.getParameter("startDate");
        String endDateStr = req.getParameter("endDate");

        if (startDateStr != null && endDateStr != null) {
            Date startDate = DateUtil.parse(startDateStr, DateUtil.FULL_DATE_FORMAT);
            Date endDate = DateUtil.parse(endDateStr, DateUtil.FULL_DATE_FORMAT);

            result = journalService.getJournalPeriod(startDate, endDate);
        }
        else {
            result = journalService.getFullJournalPeriod();
        }

        ServletUtil.writeJson(res, result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        JournalEntry entry = journalService.save(JournalFactory.fromRequest(req));

        ServletUtil.writeJson(res, entry);
    }
}
