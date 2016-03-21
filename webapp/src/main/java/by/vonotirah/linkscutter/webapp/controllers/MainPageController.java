package by.vonotirah.linkscutter.webapp.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.webapp.models.SearchLinkModel;

@Controller
public class MainPageController {

	@Inject
	private LinkService linkService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public static String mainPage(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		model.addAttribute("searchLink", new SearchLinkModel());
		if (error != null) {
			model.addAttribute("error", "Invalid username or password!");
		}
		if (logout != null) {
			model.addAttribute("logout", "You've been logged out successfully.");
		}
		return "main_page";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

		System.out.println(request);
		System.out.println(response);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/?logout";
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public ResponseEntity<Object> redirectToUrl(@PathVariable("code") String code) throws URISyntaxException {

		URI uri = linkService.linkRedirect(code);

		// убрать
		if (uri.getScheme() == null) {
			String protocol = "http://";
			uri = new URI(protocol.concat(uri.toString()));
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

	}

}
