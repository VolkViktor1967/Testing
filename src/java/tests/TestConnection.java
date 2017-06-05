/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author User
 * Проблемы:  
 * 1. Вынужден прописывать имя базы данных:  from Library.book
 * 2. Не понимаю что такое  java:comp/env/  
 * 3. Похоже не использую запись в web.xml ( <resource-ref>) Это как то связано с п.2
 * 
 * 
 */
public class TestConnection {
    public void check(){
        try{
            InitialContext is = new InitialContext();
            //DataSource ds = (DataSource) is.lookup("java:comp/env/jdbc/Library");
            DataSource ds = (DataSource) is.lookup("jdbc/Library");
            //DataSource ds = (DataSource) is.lookup("java:comp/env");
            Connection con = ds.getConnection();
            Statement  stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Library.book");
            //ResultSet rs = stmt.executeQuery("select * from book");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        }
        catch(SQLException ex){
            System.out.println("sql error"+ex.getMessage());
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NamingException ex){
            System.out.println("naming error"+ex.getMessage());
            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
