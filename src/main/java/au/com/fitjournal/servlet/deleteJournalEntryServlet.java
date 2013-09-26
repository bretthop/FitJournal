package au.com.fitjournal.servlet;

import au.com.fitjournal.service.JournalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO: Change to rest DELETE /journal/:id
// ALSO NOTE: this is horribly, horribly dirty... filthy even. But its 8 at night and the train rides almost over,
//            so i want this to work by any means necessary! There are already many TODOs dealing with refactoring
//            the use of these servlets in favour of some framework
@WebServlet(urlPatterns = "/deleteJournalEntry")
public class DeleteJournalEntryServlet extends HttpServlet
{
    JournalService journalService = new JournalService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        long id = Long.parseLong(req.getParameter("id"));

        journalService.deleteEntry(id);
    }
}
