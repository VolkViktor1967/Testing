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
 * @ganre User
 */
public class GanreList {
    private ArrayList<Ganre> ganreList = new ArrayList<Ganre>();
    
    private ArrayList<Ganre> getGanre(){

         /*
         Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Шевкун ", " ВОЛК В В");
         Logger log = Logger.getLogger(Database.class.getName());
         // Строковое сообщение
         String stringMessage = "Сообщение для Волка";
         // Вывести сообщение с указанием уровня логгирования
         log.log(Level.INFO, stringMessage);
        */
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        
        try {
            conn = Database.getConnection();
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from library.genre");
            while (rs.next()){
                Ganre ganre = new Ganre();
                ganre.setName(rs.getString("name"));
                ganre.setId(rs.getLong("id"));
                ganreList.add(ganre);
            }
                                        
        } catch (SQLException ex) {
            Logger.getLogger(GanreList.class.getName()).log(Level.SEVERE, null, " **********************  ");
        } 
        finally{
            try {
                if (stmt!=null) {
                     stmt.close();
                }
                if (rs!=null) {
                    rs.close();
                }
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GanreList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ganreList;
    }
    public  ArrayList<Ganre> getGanreList(){
        if (!ganreList.isEmpty()){
            return ganreList;
        }
        else{
            return getGanre();
        }
            
    }
}
