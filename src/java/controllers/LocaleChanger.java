/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.context.FacesContext;


/**
 *
 * @author User
 */
@Named(value = "localeChanger")
@SessionScoped
public class LocaleChanger implements Serializable {

    /**
     * Creates a new instance of LocaleChanger
     */
    
    private Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getCurrentLocale() {
        return currentLocale;
    }
    
    public LocaleChanger() {
        currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }
    
    public void changeLocale(String localeCode){
        currentLocale = new Locale(localeCode);
    }
}
