package name.wuxiaodong01.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListener implements ServletRequestListener,ServletRequestAttributeListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
//        System.out.println(String.format(Locale.ENGLISH, "request destroyed:%s", sre.getServletRequest()));
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
//        System.out.println(String.format(Locale.ENGLISH, "request created:%s", sre.getServletRequest()));

    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
//        System.out.println(String.format(Locale.ENGLISH, "attribute add to request:%s", srae.getName()));

    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
//        System.out.println(String.format(Locale.ENGLISH, "attribute remove from request:%s", srae.getName()));

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
//        System.out.println(String.format(Locale.ENGLISH, "attribute update in request:%s", srae.getName()));
    }
}
