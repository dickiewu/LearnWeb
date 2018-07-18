package name.wuxiaodong01.listener;

import name.wuxiaodong01.infrastructure.SessionRegistry;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener
public class SessionListener implements HttpSessionListener,HttpSessionIdListener,HttpSessionAttributeListener {

    @Override
    public void sessionIdChanged(HttpSessionEvent se, String oldSessionId) {
        System.out.println("session id changed, from:"+oldSessionId+", to:"+se.getSession().getId());
        SessionRegistry.updateSession(oldSessionId,se.getSession());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("create a new session, sessionId: "+se.getSession().getId());
        SessionRegistry.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("destroy a session, sessionId: "+se.getSession().getId());
        SessionRegistry.removeSesison(se.getSession());
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("attribute added,name:"+se.getName());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("attribute remove");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("attribute replace:"+se.getName());
    }
}
