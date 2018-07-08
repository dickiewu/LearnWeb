package name.wuxiaodong01.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet(name = "simpleServlet",urlPatterns = {"/simpleServlet"}, initParams = {@WebInitParam(name = "port",value = "8080")})
public class SimpleServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("simple service init");
    }

    @Override
    public ServletConfig getServletConfig() {

        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("simple service method.......");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
