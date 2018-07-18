package name.wuxiaodong01.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println(String.format(Locale.ENGLISH, "authenticate whether user login"));
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        if (session == null && session.getAttribute("user") == null) {  //用户没有登录
            System.out.println(String.format(Locale.ENGLISH, "authentication result: user have not login!!"));
            ((HttpServletResponse) resp).sendRedirect("login");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}