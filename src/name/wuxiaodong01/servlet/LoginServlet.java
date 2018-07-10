package name.wuxiaodong01.servlet;

import com.google.common.collect.Maps;
import name.wuxiaodong01.utils.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private Map<String,String> userDatabase;
    public LoginServlet(){
        userDatabase = Maps.newHashMap();
        userDatabase.put("tom","tom");
        userDatabase.put("jerry","tom");
        userDatabase.put("sila","tom");
        userDatabase.put("maria","tom");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //表单提交
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //直接取数据库, 不用判断
        String passwordStored = userDatabase.get(username);
        if(Objects.equals(passwordStored,password)){
            //验证成功,重定向到ticket界面
            processLogin(request, response, username);
        }else{
            //转发到登录界面，提示错误
            request.setAttribute("loginSuccess",false);
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        }
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response, String username) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        request.changeSessionId();
        response.sendRedirect("ticketServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断是否要执行登出操作
        String logout = request.getParameter("logout");
        if(Strings.isEmpty(logout)){  // 登出逻辑
            processLogout(request, response);
            return;
        }

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (Strings.isNullOrEmpty(username)) { // 用户还没有登录
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }else{
            response.sendRedirect("ticketServlet");
        }


    }

    private void processLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("login");
    }
}