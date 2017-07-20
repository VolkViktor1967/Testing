/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import enums.SearchType;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author User
 */
@Named(value = "searchTypeController")
@RequestScoped
public class SearchTypeController {

    
    private static Map<String, SearchType> searchList = new HashMap<String, SearchType>();
    
    public SearchTypeController() {
         ResourceBundle bandle = ResourceBundle.getBundle("lns.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
         searchList.clear();
         searchList.put(bandle.getString("author_name"), SearchType.AUTHOR);
         searchList.put(bandle.getString("book_name"), SearchType.TITLE);
    }

    public Map<String, SearchType> getSearchList() {
        return searchList;
    }

    public void setSearchList(Map<String, SearchType> searchList) {
        SearchTypeController.searchList = searchList;
    }
    
    /* public void setSearchList(Map<String, SearchType> searchList) {
        SearchController.searchList = searchList;
    }

    public Map<String, SearchType> getSearchList() {
        return searchList;
    }*/
    
    
    
}
