package name.wuxiaodong01.servlet;

import com.google.common.io.Files;
import name.wuxiaodong01.domain.User;
import name.wuxiaodong01.utils.Strings;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Objects;

@WebServlet(name = "firstServlet", urlPatterns = {"/firstServlet", "/system/bin/*"})
public class FirstServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        processResponse(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("receive post");
        doGet(req, resp);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String el = request.getParameter("el");
        if(Strings.isEmpty(el)){ //
            User user = new User(19384, "Coder314", "&John", "<>Smith");
            Hashtable<String, Boolean> permissions = new Hashtable<>();
            permissions.put("user",true);
            permissions.put("moderator",true);
            permissions.put("admin",false);
            user.setPermissions(permissions);
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("el.jsp").forward(request,response);
        }
    }


    private void processResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (Objects.equals(action, "download")) {
            String fileName = "广告.jpg";
            response.setContentType(getServletContext().getMimeType(fileName));
            response.addHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName));
            String realPath = getServletContext().getRealPath("/image/1.jpg");
            if (!Strings.isNullOrEmpty(realPath)) {
                byte[] bytes = Files.toByteArray(new File(realPath));
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes);
            }
        } else if (Objects.equals(action, "redirect")) {
            response.sendRedirect("/learnweb/index.html");
        } else if (Objects.equals(action, "refresh")) {
            response.addHeader("refresh", "5;url=http://www.baidu.com");
        } else if (Objects.equals(action, "other")) {
            System.out.println("aaa---" + Thread.currentThread().getName() + "--" + Thread.currentThread().getId());

            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");

            ServletContext servletContext = getServletContext();
            String realPath = servletContext.getRealPath("/login.html");
            byte[] bytes = Files.toByteArray(new File(realPath));
            response.getOutputStream().write(bytes);
        } else if (Objects.equals(action, "cookie")) {
            Cookie[] cookies = request.getCookies();
            int id = cookies == null ? 0 : cookies.length;
            Cookie newCookie = new Cookie("rememusername" + id, "tom" + id);
            response.addCookie(newCookie);
        }else{
            response.getWriter().println("success!!!");
        }
    }

    @Override
    public void destroy() {
        super.destroy();

    }
}