package name.wuxiaodong01.domain;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

public class User implements Serializable,HttpSessionBindingListener,HttpSessionActivationListener {
    private long userId;

    private String username;

    private String firstName;

    private String lastName;

    private Map<String, Boolean> permissions = new Hashtable<>();

    public User() {

    }

    public User(long userId, String username, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, Boolean> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, Boolean> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return this.username + ": " + this.lastName + ", " + this.firstName;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println(String.format(Locale.ENGLISH, "bound to session,name:%s,value:%s", event.getName(),event.getValue()));

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println(String.format(Locale.ENGLISH, "unbound to session,name:%s,value:%s", event.getName(),event.getValue()));

    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println(String.format(Locale.ENGLISH, "passive User,session:%s",se.getSession()));

    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println(String.format(Locale.ENGLISH, "active User,session:%s",se.getSession()));
    }
}
