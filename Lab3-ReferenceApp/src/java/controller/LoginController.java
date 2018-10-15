/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import model.User;

/**
 *
 * @author ejzumba
 */
@Named(value = "loginController")
@SessionScoped
@ManagedBean
public class LoginController {
    
    User model;
        
    
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    public User getModel() {
        return model;
    }

    public void setModel(User model) {
        this.model = model;
    }
    
    public void authenticate(){
        
    }
    
}
