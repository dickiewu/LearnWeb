package name.wuxiaodong01.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Locale;

public class DFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(String.format(Locale.ENGLISH, "Dfilter...."));
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
