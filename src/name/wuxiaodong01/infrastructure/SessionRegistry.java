package name.wuxiaodong01.infrastructure;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class SessionRegistry {

    private static Map<String, HttpSession> SESSIONS = Maps.newConcurrentMap();

    private SessionRegistry() {

    }

    public static List<HttpSession> getAllSession(){
        return Lists.newArrayList(SESSIONS.values());
    }

    public static int getSessionSize(){
        return SESSIONS.size();
    }

    public static void addSession(HttpSession session) {
        SESSIONS.put(session.getId(), session);
    }

    public static void removeSesison(HttpSession session) {
        SESSIONS.remove(session.getId());
    }

    public static void updateSession(String oldSessionId, HttpSession newSession) {
        synchronized (SessionRegistry.class) {
            SESSIONS.remove(oldSessionId);
            SESSIONS.put(newSession.getId(), newSession);
        }
    }
}
