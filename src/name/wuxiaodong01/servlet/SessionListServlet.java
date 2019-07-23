package name.wuxiaodong01.servlet;

import name.wuxiaodong01.infrastructure.SessionRegistry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SessionListServlet",urlPatterns = {"/sessions"})
public class SessionListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HttpSession> allSession = SessionRegistry.getAllSession();
        int sessionSize = SessionRegistry.getSessionSize();
        request.setAttribute("sessionSize",sessionSize);
        request.setAttribute("allSessions",allSession);
        request.getRequestDispatcher("WEB-INF/jsp/sessions.jsp").forward(request,response);
    }
}
