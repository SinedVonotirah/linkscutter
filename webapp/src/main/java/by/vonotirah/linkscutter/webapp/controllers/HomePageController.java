package by.vonotirah.linkscutter.webapp.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.service.UserService;
import by.vonotirah.linkscutter.webapp.models.NewLinkModel;

@Controller
public class HomePageController {

	@Inject
	private UserService userService;

	@Inject
	private LinkService linkService;

	private final static String BASE_URL = "http://localhost:8080/webapp/";

	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String getHomePage(Model model, Principal principal) {

		// модель ссылки для формы
		NewLinkModel newLink = new NewLinkModel();
		model.addAttribute("newLink", newLink);

		// коллекция ссылок для таблицы
		UserAccount user = userService.getUserByLogin(principal.getName());
		model.addAttribute("links", user.getLinks());

		return "home_page";
	}

	@RequestMapping(value = "/homepage", method = RequestMethod.POST)
	public String cutLink(Model model, Principal principal, @ModelAttribute("newLink") NewLinkModel link) {

		String url = link.getUrl();

		if (link.getUrl() != "") {
			try {
				URI uri = new URI(url);
				if (uri.getScheme() == null) {
					String protocol = "http://";
					uri = new URI(protocol.concat(uri.toString()));
				}
				Link createdLink = linkService.createNewLink(uri.toString(), principal.getName(), link.getDescription(),
						link.getTags());
				String genCode = createdLink.getGenCode();
				model.addAttribute("shortlink", BASE_URL + genCode);
			} catch (URISyntaxException e) {
				model.addAttribute("urlerror", "URL FORMAT ERROR");
				e.printStackTrace();
			}
		} else {
			model.addAttribute("urlerror", "You forgot to enter a URL");
		}

		return getHomePage(model, principal);
	}

}
