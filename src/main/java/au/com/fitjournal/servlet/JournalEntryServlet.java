package au.com.fitjournal.servlet;

import au.com.fitjournal.data.entity.JournalEntry;
import au.com.fitjournal.service.JournalService;
import au.com.fitjournal.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO: Change to rest /journal/:id
@WebServlet(urlPatterns = "/journalEntry")
public class JournalEntryServlet extends HttpServlet
{
    private JournalService journalService = new JournalService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        long id = Long.parseLong(req.getParameter("id"));

        JournalEntry entry = journalService.getJournalEntry(id);

        ServletUtil.writeJson(res, entry);
    }
}