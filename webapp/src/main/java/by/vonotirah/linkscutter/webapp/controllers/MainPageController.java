package by.vonotirah.linkscutter.webapp.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.service.LinkService;

@Controller
public class MainPageController {
	
	@Inject
	private LinkService linkService;
	
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String getHomePage(Map<String, Object> model){
		CreateLinkModel link = new CreateLinkModel();
		model.put("link", link);
		return "home_page";
	}	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMainPage(ModelMap model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/homepage";			
		}else return "main_page";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}
	
    @RequestMapping(value = "/cutlink", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("link") CreateLinkModel link,
            Map<String, Object> model, Principal principal) {	         
    	if(link.getUrl() == null){
    		//обработать ошибку пустого значения
    		return "redirect:/homepage";
    	}else if((link.getUrl() != null) && (link.getDescription() == null) && (link.getTags() == null)){
    		linkService.createNewLink(link.getUrl(), principal.getName());
    	}else if ((link.getUrl() != null) && (link.getDescription() != null)){
    		linkService.createNewLink(link.getUrl(), principal.getName(), link.getDescription());
    	}
    	//доделать
    	
        return "redirect:/homepage";
    }
    
    @RequestMapping(value="/{code}", method=RequestMethod.GET)
    public ResponseEntity<Object> redirectToUrl(@PathVariable("code") String code) throws URISyntaxException {
    	Link link = linkService.getLinkByCode(code);
    	String url = link.getUrl();
    	
        URI uri = new URI(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

    }
}
