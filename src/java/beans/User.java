/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author VolkViktor1967
 * 
 */
@Named(value = "user1")
@SessionScoped

public class User implements Serializable {

    private String username;
    private String password;

    public String getUserpassword() {
        return password;
    }

    public void setUserpassword(String userpassword) {
        this.password = userpassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public User() {
    
    }
    
    public String login() {
        
        
        
        try {
        
            HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            //FacesContext context = FacesContext.getCurrentInstance().getExternalContext().getRequest().login(this.username, this.password);
            //HttpServetRequest request = ((HttpServletRequest)context.getExternalContext()).getRequest();
            origRequest.login(this.username, this.password);
            
            
        } catch (ServletException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            //String msg = #{msg.wait();}
            context.addMessage(null, new FacesMessage("Неверное имя или пароль "+this.username));
            return "index";
        }
       
        //return "login";
       //return "login_from_user";
       return "pages/books";
    }
    
    
    
    public String logout(){
        
        try {
        
            HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            origRequest.logout();
            
            
        } catch (ServletException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            //String msg = #{msg.wait();}
            context.addMessage(null, new FacesMessage("Неверное имя или пароль"));
            return "index";
        }
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //return "login";
       //return "login_from_user";
       
       Callback ddd 
       return "/index.xhtml?faces-redirect=true";
    }
    
    
}
