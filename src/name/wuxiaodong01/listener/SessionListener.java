package name.wuxiaodong01.listener;

import name.wuxiaodong01.infrastructure.SessionRegistry;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener,HttpSessionIdListener {

    @Override
    public void sessionIdChanged(HttpSessionEvent se, String oldSessionId) {
        System.out.println("session id changed, from:"+oldSessionId+", to:"+se.getSession().getId());
        SessionRegistry.updateSession(oldSessionId,se.getSession());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session created:"+se.getSession().getId());
        SessionRegistry.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session destroyed:"+se.getSession().getId());
        SessionRegistry.removeSesison(se.getSession());
    }
}
