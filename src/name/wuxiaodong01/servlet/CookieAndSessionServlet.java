package name.wuxiaodong01.servlet;

import name.wuxiaodong01.domain.Attachment;
import name.wuxiaodong01.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Locale;
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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if(Objects.equals(action,"generateSession")){
            HttpSession session = request.getSession();
            session.setAttribute("sess",new Attachment());
            session.setAttribute("user1",new User());
            session.setAttribute("user2",new User());
            System.out.println("genereate session");
        }else if(Objects.equals(action,"passive")){
            HttpSession session = request.getSession();
            System.out.println(String.format(Locale.ENGLISH, "session is New:%b",session.isNew()));
            User user = new User(2L, "wxido", "tom", "tim");
            session.setAttribute("rootUser",user);
            session.setAttribute("name","wuxiaodong");
        }else if(Objects.equals(action,"active")){
            HttpSession session = request.getSession();
            System.out.println(String.format(Locale.ENGLISH, "session is New:%b",session.isNew()));
            User rootUser = (User) session.getAttribute("rootUser");
            System.out.println(String.format(Locale.ENGLISH, "active user is:%s", rootUser));
            System.out.println(String.format(Locale.ENGLISH, "name:%s", session.getAttribute("name")));
        }
    }

    private void processResponse(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if(Objects.equals(action,"generateCookie")){
            Cookie cookie = new Cookie("last-login-time", "111111");
            response.addCookie(cookie);
            HttpSession session = request.getSession();
            session.removeAttribute("user1");
        }
    }
}