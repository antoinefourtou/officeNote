/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package officemanagementsystem;

/**
 *
 * @author antoine
 */
 
import java.sql.DriverManager;  
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.sql.PreparedStatement; 


public class SqliteDB {
   
    Connection c = null;
    Statement stmt = null;
    
    PreparedStatement pst = null;
    ResultSet rs=null;
    
    SqliteDB() {
    
        try {
            
            Class.forName("org.sqlite.JDBC");
                        
        } catch (Exception e) {
            
            //log
            System.out.println("Error: " + e.getMessage());
            
        }
    }
    
    public Connection connect() {  
       
        //SQLite connection string with database filename  
        String url = "jdbc:sqlite:MyNotes.db";  
        Connection conn = null;  
        
        try {  
            
            conn = DriverManager.getConnection(url);
            
            //log
            System.out.println("Connected to MyNotes");
            
        } catch (SQLException e) { 
            
            //log
            System.out.println(e.getMessage());  
        }  
        
        return conn;  
    }  
     
    //get last records id
    public int getLastId() {
       
        String sql = "SELECT * FROM MyNotes";  
        int lastId = 0;
        
        try {  
           
            Connection conn = this.connect();  
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql);  
              
            //loop through the result set  
            while (rs.next()) {  
                lastId++;
                    
                
            }  
                        
        } catch (SQLException e) {  
            
            //log
            System.out.println(e.getMessage());  
        }  
        
        return lastId;
    }
    
    public void update(int id, String name, String lastname) {
        
        //SQL statement for update
        String sql = "UPDATE Students SET name = ? , "
                                   + "lastname = ? "
                                   + "WHERE id = ?";
 
        try {
           
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
 
            //setting parameter
            pstmt.setString(1, name);
            pstmt.setString(2, lastname);
            
            //where id = ?
            pstmt.setInt(3, id);
            
            //update 
            pstmt.executeUpdate();
            
            //log
            System.out.println("Updated record with ID = " + Integer.toString(id));
        
        } catch (SQLException e) {
            
            //log
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(int id) {
        
        //SQL statement for delete
        String sql = "DELETE FROM Students WHERE id = ?";
 
        try {
                  
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
 
            // where id = ?
            pstmt.setInt(1, id);
            
            //update
            pstmt.executeUpdate();
                
            //log
            System.out.println("Deleted record with ID = " + Integer.toString(id));
        } catch (SQLException e) {
            
            //log
            System.out.println(e.getMessage());
        }
    }
    
    public void insert(String date, String time,String sybject,String importance, String notes) {  
        
        //SQL statement for insert
        String sql = "INSERT INTO MyNotes(date,time,sybject,importance,notes) VALUES(?,?,?,?,?)";  
   
        try {  
            Connection conn = this.connect();  
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            
            pstmt.setString(1, date);  
            pstmt.setString(2, time);
            pstmt.setString(3, sybject);
            pstmt.setString(4, importance);
            pstmt.setString(5, notes);
            pstmt.executeUpdate();
            
            rs.close();
            pstmt.close();
            
            //log
           // System.out.println("Inserted record with name = " + name + " lastname = " + lastname);
            
        } catch (SQLException e) { 
            
            //log
            System.out.println(e.getMessage());  
        }  
    }  
    
    
   
     
    public void createNewTable() {  
                  
        //SQL statement for new table
       

        String sql = "CREATE TABLE IF NOT EXISTS MyNotes (\n"  
                   + " date integer PRIMARY KEY,\n" 
                   + " time integer PRIMARY KEY,\n"
                   + " sybject text NOT NULL,\n"  
                   + " notes text NOT NULL,\n"  
                   + ");";  
          
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
  
    public void closeConnection() {
        
        try {
            
            Connection conn = this.connect();
            conn.close();
            
            //log 
            System.out.println("Closing connection");
        } catch (Exception e) {
            
            //log
            System.out.println("Error: " + e.getMessage());
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

