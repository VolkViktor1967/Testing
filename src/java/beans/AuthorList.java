/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import db.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class AuthorList {
    private ArrayList<Author> authorList = new ArrayList<Author>();
    
    private ArrayList<Author> getAuthor(){

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        
        try {
            conn = Database.getConnection();
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from library.author");
            while (rs.next()){
                Author author = new Author();
                author.setName(rs.getString("fio"));
                authorList.add(author);
            }
                                        
        } catch (SQLException ex) {
            Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally{
            try {
                if (stmt!=null) stmt.close();
                if (rs!=null) rs.close();
                if (conn!=null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return authorList;
    }
    public  ArrayList<Author> getAuthorList(){
        if (!authorList.isEmpty()){
            return authorList;
        }
        else{
            return getAuthor();
        }
            
    }
}
