/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.User;

/**
 *
 * @author ejzumba
 */
public interface DAO {
    
    public int insertUser(User user);
    
    public boolean checkUserEmail(User user);
    
    public boolean checkUserID(User user);
    
    public User getUser(User user);
    
    public String getPass(String email);
    
}
