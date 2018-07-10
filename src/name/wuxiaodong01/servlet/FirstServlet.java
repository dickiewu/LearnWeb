package name.wuxiaodong01.servlet;

import com.google.common.base.Strings;
import com.google.common.io.Files;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Objects;

@WebServlet(name = "firstServlet", urlPatterns = {"/firstServlet", "/system/bin/*"}, initParams = {@WebInitParam(name = "ip", value = "192.168.1.1")})
public class FirstServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        processInit();
    }

    private void processInit() {
        System.out.println("init servlet: " + getServletName());
        ServletContext servletContext = getServletContext();
        Enumeration<String> attributeNames = servletContext.getInitParameterNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            System.out.println("servlet context name:" + name + ",value:" + servletContext.getInitParameter(name));
        }

        servletContext.setInitParameter("name", "dickie");
        System.out.println(servletContext.getInitParameter("name"));
        log("processINit.....");
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

    private void processRequest(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        //        traverseParameter(req);
        String contextPath = getServletContext().getContextPath();
        System.out.println("context path:"+contextPath);
        String characterEncoding = req.getCharacterEncoding();
        System.out.println("character encoding:"+characterEncoding);
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        System.out.println("origin username is :"+username);

        String action = req.getParameter("action");
        if (Objects.equals(action, "dispatch")) {
            System.out.println("dispatch....");
            req.setAttribute("from", "firstServlet");
            System.out.println("request:" + req + ",response:" + response);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dispatchServlet");
            requestDispatcher.forward(req, response);
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
            /*resp.setStatus(302);
            resp.addHeader("location","http://www.baidu.com");*/
            response.sendRedirect("/learnweb/index.html");
        } else if (Objects.equals(action, "refresh")) {
            response.addHeader("refresh", "5;url=http://www.baidu.com");
        } else if(Objects.equals(action,"other")) {
            System.out.println("aaa---" + Thread.currentThread().getName() + "--" + Thread.currentThread().getId());

            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");

            ServletContext servletContext = getServletContext();
            String realPath = servletContext.getRealPath("/login.html");
            byte[] bytes = Files.toByteArray(new File(realPath));
            boolean committed = response.isCommitted();
            System.out.println("commited:" + committed);
            response.getOutputStream().write(bytes);
        }else if(Objects.equals(action,"cookie")){
            Cookie[] cookies = request.getCookies();
            int id = cookies==null?0:cookies.length;
            Cookie newCookie = new Cookie("rememusername"+id, "tom"+id);
            response.addCookie(newCookie);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy servlet: " + getServletName());
    }
}