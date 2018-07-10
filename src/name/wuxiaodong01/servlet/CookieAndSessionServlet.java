package name.wuxiaodong01.servlet;

import name.wuxiaodong01.domain.Attachment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "CookieAndSessionServlet", urlPatterns = "/cookieServlet")
public class CookieAndSessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
        processResponse(request,response);
    }

    private void processResponse(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if(Objects.equals(action,"generateCookie")){
            Cookie cookie = new Cookie("last-login-time", "111111");
            response.addCookie(cookie);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if(Objects.equals(action,"generateSession")){
            HttpSession session = request.getSession();
            session.setAttribute("sess",new Attachment());
            System.out.println("genereate session");
        }
    }
}
