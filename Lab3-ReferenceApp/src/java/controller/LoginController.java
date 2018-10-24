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

    public String authenticate() {
        DAOImpl = new DAOImpl();
        String pass = DAOImpl.getPass(model.getEmail());
        String retVal = pass.equals(model.getPassword()) ? "LoginGood.xhtml" : "";
        
//        if (retVal.equals("")) {
//            FacesContext.getCurrentInstance().addMessage("login:inputPassword", new FacesMessage("Username or Password does not match"));
//        }

        return retVal;
    }

    public void retriveUser() {
        DAOImpl = new DAOImpl();
        DAOUser = DAOImpl.getUser(model);
    }

    public String createUser() {
        String response = "";
        DAOImpl = new DAOImpl();
        boolean isEmail = DAOImpl.checkUserEmail(model);
        boolean isUserID = DAOImpl.checkUserID(model);
        boolean isPasswordMatch = model.getPassword().equals(model.getConfirmPassword());
        if (isEmail) {
            FacesContext.getCurrentInstance().addMessage("signUp:email", new FacesMessage("Email is already used"));
        }
        if (isUserID) {
            FacesContext.getCurrentInstance().addMessage("signUp:userID", new FacesMessage("User ID is already taken"));
        }

        if (!isPasswordMatch) {
            FacesContext.getCurrentInstance().addMessage("signUp:password", new FacesMessage("Passwords do not match"));
        }

        if (!isUserID && !isEmail && isPasswordMatch) {
            DAOImpl.insertUser(model);
            response = "echo.xhtml";
        }

        return response;
//         
    }

    public String response() {
        String response;
        DAOImpl = new DAOImpl();

        if (!DAOImpl.checkUserEmail(model)) {
            DAOImpl.insertUser(model);
        } else {
            FacesContext.getCurrentInstance().addMessage("signUp:email", new FacesMessage("Email already exists in database"));
        }

        return "";
    }

}
