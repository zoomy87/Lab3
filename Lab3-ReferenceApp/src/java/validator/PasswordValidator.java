/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.jboss.logging.Logger;



/**
 *
 * @author ejzumba
 */

@FacesValidator
public class PasswordValidator implements Validator{
    private static final Logger log= Logger.getLogger(PasswordValidator.class);

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String param = (String) component.getAttributes().get("param"); 
        param.equals((String) value);
        log.info(param);
    }
    
}
