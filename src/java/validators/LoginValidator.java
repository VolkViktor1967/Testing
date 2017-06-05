/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author User
 */
@FacesValidator("validators.UserValidator")
public class LoginValidator implements Validator {

    /**
     * Creates a new instance of LoginValidator
     */
    public LoginValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        ResourceBundle bandle = ResourceBundle.getBundle("lns.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        
        try {
           
            if (!Character.isLetter(value.toString().charAt(0))) {
                throw new IllegalArgumentException(bandle.getString("login_first_error"));
            }
            
            if (value.toString().length() < 5) {
                throw new IllegalArgumentException(bandle.getString("login_lenght_error"));
            }
            
            if (getBanList().contains(value.toString())) {
                throw new IllegalArgumentException(bandle.getString("login_username_error"));
            }
         

        } catch (IllegalArgumentException e) {
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
        
    }
    
    private ArrayList<String> getBanList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("username");
        list.add("login");
        return list;
        
    }
    
}
