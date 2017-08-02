/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Database;
import java.awt.Image;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Book implements Serializable{
    
    private boolean edit=false;
    
    private Long id;
    private String name;
    private byte[] content;
    private int    pageCount;
    private String isbn;
    private String genre;
    private String author;
    private Long   publishYear;
    private String publisher;
    private byte[] image;
    private String descr;

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

  

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public Long getPublishYear() {
        return publishYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishYear(Long publishYear) {
        this.publishYear = publishYear;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
/*
    public void setImage(Image image) {
        this.image = image;
    }
  
  */  
    
      public void fillPdfContent(){

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        /*bookList.clear();*/
        
        try {
            conn = Database.getConnection();
            
            stmt = conn.createStatement();
            long _id=this.getId();
                        
            rs = stmt.executeQuery("select content from library.book where id= "+_id);
            /*rs = stmt.executeQuery("select content from library.book where id= 1");*/
            while (rs.next()){
                
                this.setContent(rs.getBytes("content"));
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
        System.out.println("fillPdfContent end");
    }
    
    
      public void fillImageContent(){

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        
        
        try {
            conn = Database.getConnection();
            
            stmt = conn.createStatement();
            long _id=this.getId();
                        
            rs = stmt.executeQuery("select image from library.book where id= "+_id);
            
            while (rs.next()){
                
                this.setImage(rs.getBytes("image"));
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
        System.out.println("fillPdfContent end");
    }
    
      
}
