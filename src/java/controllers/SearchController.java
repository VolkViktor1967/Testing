/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import enums.SearchType;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;


/**
 *
 * @author User
 */

@Named(value = "searchController1")
@SessionScoped
public class SearchController implements Serializable {
    
    private SearchType searchType;
    
    private static Map<String, SearchType> searchList = new HashMap<String, SearchType>();

    public void setSearchList(Map<String, SearchType> searchList) {
        SearchController.searchList = searchList;
    }

    public Map<String, SearchType> getSearchList() {
        return searchList;
    }
       

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }
    public SearchType getSearchType() {
        return searchType;
    }
        
    
    public SearchController() {
         ResourceBundle bandle = ResourceBundle.getBundle("lns.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
         searchList.put(bandle.getString("author_name"), SearchType.AUTHOR);
         searchList.put(bandle.getString("book_name"), SearchType.TITLE);
    }
    
}
