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
//@Named(value = "loginController")
@SessionScoped
@ManagedBean
public class LoginController {
    
    User model;
    User DAOUser;
    DAO DAOImpl;

    public User getDAOUser() {
        return DAOUser;
    }

    public void setDAOUser(User DAOUser) {
        this.DAOUser = DAOUser;
    }
    
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
    
    public String authenticate(){
        
        
        return "login-success.xhtml";
    }
    
    public void retriveUser(){
        DAOImpl = new DAOImpl();
        DAOUser = DAOImpl.getUser(model);
        
    }
    
    public String createUser(){
        String response;
        DAOImpl = new DAOImpl();
        boolean isEmail= !DAOImpl.checkUserEmail(model);
        boolean isUserID= DAOImpl.checkUserID(model);
        if(isEmail){
            FacesContext.getCurrentInstance().addMessage("signUp:email", new FacesMessage("Email already exists in database"));
        }
        if(isUserID){
            FacesContext.getCurrentInstance().addMessage("signUp:userID", new FacesMessage("userID Error"));
            }
        if(!isUserID && !isEmail){       
            DAOImpl.insertUser(model);
            response= "login-success.xhtml";
        }
        
        return "";        
//         
    }
    
    public String response(){
        String response;
        DAOImpl = new DAOImpl();
        
        if(!DAOImpl.checkUserEmail(model)){
            DAOImpl.insertUser(model);
        }
        else{
            FacesContext.getCurrentInstance().addMessage("signUp:email", new FacesMessage("Email already exists in database"));
        }
        
        return "";
    }
    
}
