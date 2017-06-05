/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author User
 */
public class Database {
    private static Connection conn;
    private static InitialContext ic;
    private static DataSource ds;
    
    public static Connection getConnection(){
                      
            try {
               ic = new InitialContext();
               ds = (DataSource) ic.lookup("jdbc/Library");
               if (conn==null || conn.isClosed()){
                    conn = ds.getConnection();
               }
            } catch (SQLException | NamingException ex) {
                 Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, " ********************** ВОЛК В В ************************** ");
            }
            return  conn;
    }
}

