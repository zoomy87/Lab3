/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author ejzumba
 */
public class DAOImpl implements DAO {
    
    private final Logger log= Logger.getLogger(DAOImpl.class.getName());
    
    
    public int insertUser(User user){
        int rowCount = 0;
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        try {
            String myDB = "jdbc:derby://localhost:1527/profile";// connection string
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO users (fname, lname, userid, password, queation, answer, email) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = DBConn.prepareStatement(insertString);
            pstmt.setString(1, user.getfName() );
            pstmt.setString(2, user.getlName() );
            pstmt.setString(3, user.getUserID() );
            pstmt.setString(4, user.getPassword() );
            pstmt.setString(5, user.getQuestion() );
            pstmt.setString(6, user.getAnswer());
            pstmt.setString(7, user.getEmail());

            rowCount = pstmt.executeUpdate(); //stmt.executeUpdate(insertString);
            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }
    
    public boolean checkUserEmail(User user){
        boolean userExist = false;
        Statement stmt;
        ResultSet rs;
        String query;
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        
        try {
            String myDB = "jdbc:derby://localhost:1527/profile";// connection string
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            stmt = DBConn.createStatement();
            query = "SELECT * FROM users WHERE userid ='"+ user.getEmail()+ "'";
            rs = stmt.executeQuery(query);
            if(rs.next())
                userExist = true;
            DBConn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        
        return userExist;
    }
    
        public boolean checkUserID(User user){
        boolean userExist = false;
        Statement stmt;
        ResultSet rs;
        String query;
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        
        try {
            String myDB = "jdbc:derby://localhost:1527/profile";// connection string
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            stmt = DBConn.createStatement();
            query = "SELECT * FROM users WHERE userid ='"+ user.getUserID() + "'";
            rs = stmt.executeQuery(query);
            if(rs.next())
                userExist = true;
            DBConn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        
        return userExist;
    }
}
