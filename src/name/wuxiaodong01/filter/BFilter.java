package name.wuxiaodong01.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Locale;

public class BFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println(String.format(Locale.ENGLISH, "BFilter...."));
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
