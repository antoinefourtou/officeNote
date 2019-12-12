package officemanagementsystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antoine
 */
public class MyNoteDB {
    
    Connection c = null;
    Statement stmt = null;
    
    PreparedStatement pst = null;
    ResultSet rs=null;
    
    MyNoteDB() {
    
        try {
            
            Class.forName("org.sqlite.JDBC");
                        
        } catch (Exception e) {
            
            //log
            System.out.println("Error: " + e.getMessage());
            
        }
    }
    
    public static Connection connect() {  
       
        //SQLite connection string with database filename  
        String url = "jdbc:sqlite:antoine.db";  
        Connection conn = null;  
        
        try {  
            
            conn = DriverManager.getConnection(url);
            
            //log
            System.out.println("Connected to antoine");
            
        } catch (SQLException e) { 
            
            //log
            System.out.println(e.getMessage());  
        }  
        
        return conn;  
    }  
     
    
    
    
    
   
     
    public void createNewTable() {  
                  
        //SQL statement for new table
       

        String sql = "CREATE TABLE IF NOT EXISTS MYNOTES ( date text ,time text ,sybject text NOT NULL, importance text NOT NULL , notes text NOT NULL , PRIMARY KEY (date,time))";  
          
        try {  
           
           Connection conn = this.connect();
           Statement stmt = conn.createStatement();  
           stmt.execute(sql);
           
           //log
           System.out.println("MyNotes table is ready");
           
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
  
    
  
    
    
    public void executeQuery(String query) {
        try {
            
            stmt = c.createStatement();
            stmt.executeQuery(query);
            
        } catch (SQLException e) {
            
            //log
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
