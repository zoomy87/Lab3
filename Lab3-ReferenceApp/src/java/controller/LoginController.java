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
import dao.DAO;
import dao.DAOImpl;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ejzumba
 */
@Named(value = "loginController")
@SessionScoped
@ManagedBean
public class LoginController {
    
    User model;
    DAO DAOImpl;
    
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        model = new User();
    }

    public User getModel() {
        return model;
    }

    public void setModel(User model) {
        this.model = model;
    }
    
    public void authenticate(){
        
    }
    
    public void createUser(){
        FacesContext.getCurrentInstance().addMessage("signUp:email", new FacesMessage("Email Error"));
        FacesContext.getCurrentInstance().addMessage("signUp:userID", new FacesMessage("userID Error"));
    }
    
    public String response(){
        String response;
        DAOImpl = new DAOImpl();
        
        if(!DAOImpl.checkUserEmail(model)){
            DAOImpl.insertUser(model);
        }
        else{
            
        }
        
        return "";
    }
    
}
