package name.wuxiaodong01.filter;

import name.wuxiaodong01.utils.Strings;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if(session == null || Strings.isNullOrEmpty((String) session.getAttribute("username"))){ // 没有登录
            System.out.println(String.format(Locale.ENGLISH, "用户没有登录"));
            ((HttpServletResponse) response).sendRedirect("login");
            return;
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
