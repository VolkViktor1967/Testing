/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Book;
import beans.BookList;
import db.Database;
import enums.SearchType;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.jasper.tagplugins.jstl.ForEach;


/**
 *
 * @author User
 */


/*@ManagedBean(eager = true)
@SessionScoped*/

@Named(value = "searchController")
@SessionScoped

public class SearchController implements Serializable {
    
    private boolean requestFromPage=false;
    private int bookOnPage =2;
    private int selectedGenreId;
    private char selectedLetter;
    private long selectedPageNumber=1;
    private long totalBookCount;
    private ArrayList<Integer> pageNambers = new ArrayList<>();
    
    
    private SearchType searchType;
    private static Map<String, SearchType> searchList = new HashMap<String, SearchType>();
    private ArrayList<Book> currentBookList;
    private Character[] russianLetters;
    private String searchString;
    private String currentSql;
    
    
    public SearchController() {
        
         getAllBook();
         ResourceBundle bandle = ResourceBundle.getBundle("lns.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
         searchList.put(bandle.getString("author_name"), SearchType.AUTHOR);
         searchList.put(bandle.getString("book_name"), SearchType.TITLE);
         
    }
    

    public void setBookOnPage(int bookOnPage) {
        this.bookOnPage = bookOnPage;
    }

    public void setSelectedGenreId(int selectedGenreId) {
        this.selectedGenreId = selectedGenreId;
    }

    public void setSelectedLetter(char selectedLetter) {
        this.selectedLetter = selectedLetter;
    }

    public void setSelectedPageNumber(long selectedPageNumber) {
        this.selectedPageNumber = selectedPageNumber;
    }

    public void setTotalBookCount(long totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public void setPageNambers(ArrayList<Integer> pageNambers) {
        this.pageNambers = pageNambers;
    }

    public int getBookOnPage() {
        return bookOnPage;
    }

    public int getSelectedGenreId() {
        return selectedGenreId;
    }

    public char getSelectedLetter() {
        return selectedLetter;
    }

    public long getSelectedPageNumber() {
        return selectedPageNumber;
    }

    public long getTotalBookCount() {
        return totalBookCount;
    }

    public ArrayList<Integer> getPageNambers() {
        return pageNambers;
    }

    public void setRussianLetters(Character[] russianLetters) {
        this.russianLetters = russianLetters;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }
    
    public byte[] GetImage(int index){
        
        Book book = currentBookList.get(index);
        return book.getImage();
    }

    public void setCurrentBookList(ArrayList<Book> currentBookList) {
        this.currentBookList = currentBookList;
    }

    public ArrayList<Book> getCurrentBookList() {
        return currentBookList;
    }

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
        
    
        
    private void FillBookBySql(String sqlstr){
        
        StringBuilder sqlBuilder = new StringBuilder(sqlstr);
        currentSql = sqlstr;
                

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        /*bookList.clear();*/
        
        try {
            conn = Database.getConnection();
            stmt = conn.createStatement();
            
            if (!requestFromPage) {
                rs = stmt.executeQuery(sqlBuilder.toString());
                rs.last();
                totalBookCount = rs.getRow();
                fillPageNumbers(totalBookCount, bookOnPage);

            }
            
            if (totalBookCount>bookOnPage){ 
                sqlBuilder.append(" limit ").append((selectedPageNumber)*bookOnPage-bookOnPage).append(",").append(bookOnPage);
            }
            
            rs = stmt.executeQuery(sqlBuilder.toString());
            
            currentBookList = new ArrayList<Book>();
            
            while (rs.next()){
                Book book = new Book();
                
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setPageCount(rs.getInt("page_count"));
                book.setIsbn(rs.getString("isbn"));
                book.setGenre(rs.getString("genre"));
                book.setAuthor(rs.getString("author"));
                //book.setPublishYear(rs.getLong("publish_year"));
                book.setPublisher(rs.getString("publisher"));
                book.setImage(rs.getBytes("image"));
                
                
                currentBookList.add(book);
            }
                                        
        } catch (SQLException ex) {
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally{
            try {
                if (stmt!=null) stmt.close();
                if (rs!=null) rs.close();
                if (conn!=null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(currentBookList.size());
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        session.setAttribute("currentBookList", currentBookList);
        
        ArrayList<Book> list = (ArrayList<Book>)session.getAttribute("currentBookList");
        return ;
    }
    
    public  void getBookList(){
        if (!currentBookList.isEmpty()){
            //return currentBookList;
        }
        else{
            FillBookBySql("select * from library.book");
        }
            
    }
    
    
    public  void getAllBook(){
        
        FillBookBySql("select b.id, b.name, b.isbn, b.page_count, g.name as genre, a.fio as author, p.name as publisher\n,b.image,b.publish_year "
                + "from library.book b\n"
                + "left join library.author a on a.id=b.author_id\n"
                + "left join library.genre g on g.id=b.genre_id\n"
                + "left join library.publisher p on p.id=b.publisher_id\n"
                + " ");
                //+ "limit 0,2; ");
                selectedGenreId =-1;
    }
    
    public  void fillBookByGenre(){
        
        //long id_long;
        FacesContext context = FacesContext.getCurrentInstance();
        String id = context.getExternalContext().getRequestParameterMap().get("genre_id");
        
        selectedGenreId = Integer.valueOf(id);
        selectedPageNumber=1;
        requestFromPage=false;
                
        System.out.println("Id="+id);
        FillBookBySql("select b.id, b.name, b.isbn, b.page_count, g.name as genre, a.fio as author, p.name as publisher\n,b.image "
                + "from library.book b\n"
                + "left join library.author a on a.id=b.author_id\n"
                + "left join library.genre g on g.id=b.genre_id\n"
                + "left join library.publisher p on p.id=b.publisher_id\n"
                + "where b.genre_id= \n"+id+"\n"
                + " ");
    }
    //getBookBySearch
     public  void FillBookBySearch(){
        
        
        selectedPageNumber=1;
        requestFromPage=false;
        
        if (searchString.trim().length()==0){
            getAllBook();
            return;
        }
        
        StringBuilder sql = new StringBuilder("select b.id, b.name, b.isbn, b.page_count, g.name as genre, a.fio as author, p.name as publisher\n,b.image "
                + "from library.book b\n"
                + "left join library.author a on a.id=b.author_id\n"
                + "left join library.genre g on g.id=b.genre_id\n"
                + "left join library.publisher p on p.id=b.publisher_id\n");
        
        if (searchType.equals(SearchType.AUTHOR)) {
            sql.append("where lower(a.fio) like '%").append(searchString.toLowerCase()).append("%'");;
        }
        else {
            sql.append("where lower(b.name) like '%").append(searchString.toLowerCase()).append("%'");
        }
        sql.append(" ");
        
        FillBookBySql(sql.toString());
        
        //selectedLetter=' ';
        selectedGenreId =-1;
        //selectedPageNumber=1;
        
    }
    
     public  void FillBookByLetters(){

        FacesContext context = FacesContext.getCurrentInstance();
        String letter = context.getExternalContext().getRequestParameterMap().get("letter");       
        
        selectedPageNumber=1;
        requestFromPage=false;
        
        StringBuilder sql = new StringBuilder("select b.id, b.name, b.isbn, b.page_count, g.name as genre, a.fio as author, p.name as publisher\n,b.image "
                + "from library.book b\n"
                + "left join library.author a on a.id=b.author_id\n"
                + "left join library.genre g on g.id=b.genre_id\n"
                + "left join library.publisher p on p.id=b.publisher_id\n");
        
        
        sql.append("where  substr(b.name,1,1)='").append(letter.toLowerCase()).append("'");
        sql.append(" ");
        
        FillBookBySql(sql.toString());
        selectedGenreId =-1;
    }
     
     public Character[] getRussianLetters(){
        Character[] letters =  new Character[33];
        letters[0]='А';
        letters[1]='Б';
        letters[2]='В';
        letters[3]='Г';
        letters[4]='Д';
        letters[5]='Е';
        letters[6]='Е';
        letters[7]='Ж';
        letters[8]='З';
        letters[9]='И';
        letters[10]='Й';
        letters[11]='К';
        letters[12]='Л';
        letters[13]='М';
        letters[14]='Н';
        letters[15]='О';
        letters[16]='П';
        letters[17]='Р';
        letters[18]='С';
        letters[19]='Т';
        letters[20]='У';
        letters[21]='Ф';
        letters[22]='Х';
        letters[23]='Ц';
        letters[24]='Ч';
        letters[25]='Ш';
        letters[26]='Щ';
        letters[27]='Ъ';
        letters[28]='Ы';
        letters[29]='Ь';
        letters[30]='Э';
        letters[31]='Ю';
        letters[32]='Я';
        
        return letters;
    }
     
     
     public void selectPage(){
        FacesContext context = FacesContext.getCurrentInstance();
        selectedPageNumber = Integer.valueOf(context.getExternalContext().getRequestParameterMap().get("page_namber"));       
        requestFromPage=true;
        FillBookBySql(currentSql);      
     }

    private void fillPageNumbers(long totalBookCount, int bookOnPage) {
     
        int pageCount = totalBookCount>0 ? (int)(totalBookCount/bookOnPage):0;
        pageNambers.clear();
        if ((totalBookCount%bookOnPage)>0)  pageCount++;

        for (int i=1; i<=pageCount;i++){
            pageNambers.add(i);
        }
        
    }
     
}
