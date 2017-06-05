/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Database;
import enums.SearchType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @book User
 */
public class BookList {
    
    private ArrayList<Book> bookList = new ArrayList<Book>();
    
    private ArrayList<Book> getBook(String sqlstr){

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        /*bookList.clear();*/
        
        try {
            conn = Database.getConnection();
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlstr);
            while (rs.next()){
                Book book = new Book();
                
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setPageCount(rs.getInt("page_count"));
                book.setIsbn(rs.getString("isbn"));
                book.setGenre(rs.getString("genre"));
                book.setAuthor(rs.getString("author"));
                /*book.setPublishDate(rs.getDate("publishdate"));*/
                book.setPublisher(rs.getString("publisher"));
                book.setImage(rs.getBytes("image"));
                
                
                bookList.add(book);
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
        System.out.println("bookList");
        return bookList;
    }
    
    public  ArrayList<Book> getBookList(){
        if (!bookList.isEmpty()){
            return bookList;
        }
        else{
            return getBook("select * from library.book");
        }
            
    }
    
    
    public  ArrayList<Book> getAllBook(){
        
        return getBook("select b.id, b.name, b.isbn, b.page_count, g.name as genre, a.fio as author, p.name as publisher\n,b.image "
                + "from library.book b\n"
                + "left join library.author a on a.id=b.author_id\n"
                + "left join library.genre g on g.id=b.genre_id\n"
                + "left join library.publisher p on p.id=b.publisher_id\n"
                + "limit 0,6; ");
    }
    
    public  ArrayList<Book> getBookByGenre(long id){
        
        System.out.println("Id="+id);
        return getBook("select b.id, b.name, b.isbn, b.page_count, g.name as genre, a.fio as author, p.name as publisher\n,b.image "
                + "from library.book b\n"
                + "left join library.author a on a.id=b.author_id\n"
                + "left join library.genre g on g.id=b.genre_id\n"
                + "left join library.publisher p on p.id=b.publisher_id\n"
                + "where b.genre_id= \n"+id+"\n"
                + "limit 0,6; ");
    }
    //getBookBySearch
     public  ArrayList<Book> getBookBySearch(String searchstr,SearchType type){
        
        System.out.println("getBookBySearch with "+searchstr+" "+type);
        StringBuilder sql = new StringBuilder("select b.id, b.name, b.isbn, b.page_count, g.name as genre, a.fio as author, p.name as publisher\n,b.image "
                + "from library.book b\n"
                + "left join library.author a on a.id=b.author_id\n"
                + "left join library.genre g on g.id=b.genre_id\n"
                + "left join library.publisher p on p.id=b.publisher_id\n");
        
        if (type.equals(SearchType.AUTHOR)) {
            sql.append("where lower(a.fio) like '%").append(searchstr.toLowerCase()).append("%'");;
        }
        else {
            sql.append("where lower(b.name) like '%").append(searchstr.toLowerCase()).append("%'");
        }
        sql.append(" limit 0,6;");
        
        return getBook(sql.toString());
    }
    
     public  ArrayList<Book> getBookByLetters(String letters){
        
        System.out.println("getBookBySearch with "+letters);
        StringBuilder sql = new StringBuilder("select b.id, b.name, b.isbn, b.page_count, g.name as genre, a.fio as author, p.name as publisher\n,b.image "
                + "from library.book b\n"
                + "left join library.author a on a.id=b.author_id\n"
                + "left join library.genre g on g.id=b.genre_id\n"
                + "left join library.publisher p on p.id=b.publisher_id\n");
        
        
        sql.append("where  substr(b.name,1,1)='").append(letters.toLowerCase()).append("'");
        sql.append(" limit 0,6;");
        
        return getBook(sql.toString());
    }
     
}
