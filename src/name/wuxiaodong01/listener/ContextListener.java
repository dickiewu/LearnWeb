package name.wuxiaodong01.listener;

import javax.servlet.*;
import java.util.Locale;


public class ContextListener implements ServletContextListener,ServletContextAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(String.format(Locale.ENGLISH, "contextInitialized :%s", sce.getServletContext()));
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("loadTime",System.currentTimeMillis());
        servletContext.setAttribute("loadTime","hello owrld");
        onApplicationCreate();

    }

    private void onApplicationCreate() {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(String.format(Locale.ENGLISH, "contextDestroyed:%s", sce.getServletContext()));

    }


    @Override
    public void attributeAdded(ServletContextAttributeEvent scab) {
        System.out.println(String.format(Locale.ENGLISH, "attribute add to context:%s", scab.getName()));

    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scab) {
        System.out.println(String.format(Locale.ENGLISH, "attribute remove from context:%s", scab.getName()));

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scab) {
        System.out.println(String.format(Locale.ENGLISH, "attribute update in context:%s, value:%s", scab.getName(),scab.getValue()));
    }
}
