package controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Simple navigation bean
 * @author itcuties
 *
 */
@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1520318172495977648L;

	/**
	 * Redirect to login page.
	 * @return Login page name.
	 */
	public String redirectToLogin() {
		return "/login.xhtml?faces-redirect=true";
	}
	
	/**
	 * Go to login page.
	 * @return Login page name.
	 */
	public String toLogin() {
		return "/login.xhtml";
	}
	
	/**
	 * Redirect to info page.
	 * @return Info page name.
	 */
	public String redirectToInfo() {
		return "/LoginGood.xhtml?faces-redirect=true";
	}
	
	/**
	 * Go to info page.
	 * @return Info page name.
	 */
	public String toInfo() {
		return "/LoginGood.xhtml";
	}
	
	
	public String redirectToEcho() {
		return "/echo.xhtml?faces-redirect=true";
	}
	
	
	public String toEcho() {
		return "/echo.xhtml";
	}
	
        public String redirectToUpdate() {
		return "/update.xhtml?faces-redirect=true";
	}
	
	
	public String toUpdate() {
		return "/update.xhtml";
	}
}
