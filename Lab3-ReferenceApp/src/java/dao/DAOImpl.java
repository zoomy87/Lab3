/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
            insertString = "INSERT INTO Profile.Users VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = DBConn.prepareStatement(insertString);
            pstmt.setString(1, user.getfName() );
            pstmt.setString(2, user.getlName() );
            pstmt.setString(3, user.getUserID() );
            pstmt.setString(4, user.getPassword() );
            pstmt.setString(5, user.getQuestion() );
            pstmt.setString(6, user.getAnswer());

            rowCount = pstmt.executeUpdate(); //stmt.executeUpdate(insertString);
            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }
    
    public boolean checkEmail(User user){
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        
        try {
            String myDB = "jdbc:derby://localhost:1527/profile";// connection string
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            //todo make select statement to verify user does not exist check if null return true
            DBConn.close();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
