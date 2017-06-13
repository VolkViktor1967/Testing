/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.User;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */
@ManagedBean
@RequestScoped

public class LoginControllers {

    /**
     * Creates a new instance of LoginControllers
     */
    public LoginControllers() {
    }
    
    public String login(){
        return "login";
    }
    public String exit(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //User userTmp = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("User");
        //userTmp.setUsername("");
        
        return "exit";
    }
}
