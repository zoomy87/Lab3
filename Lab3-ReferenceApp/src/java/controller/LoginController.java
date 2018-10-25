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
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.jboss.logging.Logger;

/**
 *
 * @author ejzumba
 */
//@Named(value = "loginController")
@SessionScoped
@ManagedBean
public class LoginController {

    private static final Logger log = Logger.getLogger(LoginController.class);
    private User model;
    private User DAOUser;
    private DAO DAOImpl;
    private Map<String, Integer> loginTries = new HashMap<>();

    ;

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
        String retVal = "";
        int x = 1;

        if (pass.equals(model.getPassword())) {
            retVal = "LoginGood.xhtml?faces-redirect=true";
        } else if (!loginTries.containsKey(model.getEmail())) {
            loginTries.put(model.getEmail(), x);
            FacesContext.getCurrentInstance().addMessage("login:inputPassword", new FacesMessage("Email or Password do not match"));
        } else {
            x = loginTries.get(model.getEmail());
            loginTries.put(model.getEmail(), ++x);
            FacesContext.getCurrentInstance().addMessage("login:inputPassword", new FacesMessage("Email or Password do not match"));
            if (x >= 3) {
                retVal = "LoginBad.xhtml?faces-redirect=true";
            }
        }


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
            System.out.println(model.getEmail());
            model.sendEmail();
        }

        return response;
//         
    }

//    public String response() {
//        String response;
//        DAOImpl = new DAOImpl();
//
//        if (!DAOImpl.checkUserEmail(model)) {
//            DAOImpl.insertUser(model);
//        } else {
//            FacesContext.getCurrentInstance().addMessage("signUp:email", new FacesMessage("Email already exists in database"));
//        }
//
//        return "";
//    }
    
    public String updateUser(){
        String response = "";
        DAOImpl = new DAOImpl();
        boolean isEmail = DAOImpl.checkUserEmail(DAOUser);
        boolean isUserID = DAOImpl.checkUserID(DAOUser);
        boolean isPasswordMatch = DAOUser.getPassword().equals(DAOUser.getConfirmPassword());
        
        log.info("Password: " + DAOUser.getPassword());
        if(DAOUser.getActiveEmail().equals(DAOUser.getEmail())){
            isEmail= false;
        }
        if (isEmail) {
            FacesContext.getCurrentInstance().addMessage("update:email", new FacesMessage("Email is already used"));
        }
        
        if(DAOUser.getActiveId().equals(DAOUser.getUserID())){
            isUserID= false;
        }    
        if (isUserID) {
            FacesContext.getCurrentInstance().addMessage("update:userID", new FacesMessage("User ID is already taken"));
        }

        if (!isPasswordMatch) {
            FacesContext.getCurrentInstance().addMessage("update:password", new FacesMessage("Passwords do not match"));
        }else if(DAOUser.getPassword().isEmpty()){
            DAOUser.setPassword(DAOUser.getActivePassword());
        }
        
        if (!isUserID && !isEmail && isPasswordMatch) {
            Integer i= 1;
            model= i.equals(DAOImpl.updateUser(DAOUser))? DAOUser:model;
            response= "LoginGood.xhtml?faces-redirect=true";
        }
        return response;
    }

}
