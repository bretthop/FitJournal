package au.com.fitjournal.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/journal")
public class JournalServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setHeader("Content-Type", "application/json");
        res.getWriter().write("{\"day\":\"monday\", \"food\":\"tuna\"}");
    }
}
