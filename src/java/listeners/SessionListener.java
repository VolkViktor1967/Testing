/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author User
 */
@WebListener()
    public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession sess = se.getSession();
        ServletContext sc= sess.getServletContext();
        HashMap hm = (HashMap)sc.getAttribute("sessionMap");
        hm.put(sess.getId(),sess);
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession sess = se.getSession();
        ServletContext sc= sess.getServletContext();
        HashMap hm = (HashMap)sc.getAttribute("sessionMap");
        hm.remove(sess.getId());
    }
}
