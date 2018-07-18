package name.wuxiaodong01.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;


public class LoginFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Cookie[] cookies = request.getCookies();
        for(int i = 0; cookies!=null && i<cookies.length;i++){
            Cookie cookie = cookies[i];
            String name = cookie.getName();
            if(Objects.equals(name,"user")){
                System.out.println(String.format(Locale.ENGLISH, "autologin value:%s ", cookie.getValue()));
                HttpSession session = request.getSession();
                System.out.println(String.format(Locale.ENGLISH, "login filter session is New:%b", session.isNew()));
                session.setAttribute("username",cookie.getValue());
                break;
            }
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {

    }
}
