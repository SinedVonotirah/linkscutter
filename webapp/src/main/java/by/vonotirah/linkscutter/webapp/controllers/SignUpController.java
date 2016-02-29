package by.vonotirah.linkscutter.webapp.controllers;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.UserService;

@Controller
public class SignUpController {
	
	@Inject
	private UserService userService; 
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {
	    UserAccount userForm = new UserAccount();    
	    model.put("userForm", userForm);	         
	    return "signup";
	}
	   
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("userForm") UserAccount user,
            Map<String, Object> model) {	         
    	userService.createNewUser(user.getLogin(), user.getMail(), user.getPassword());         
        return "main";
    }
}
