package by.vonotirah.linkscutter.webapp.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.webapp.models.SearchLinkModel;

@Controller
public class LinksDetailsController {

	@Inject
	private LinkService linkService;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchPage(@ModelAttribute("searchLink") SearchLinkModel searchLinkModel, Model model) {

		String requiredLink = searchLinkModel.getSearchUrl();
		String linkCode = requiredLink.substring(requiredLink.length() - 6);
		if (linkService.chekLinkExistByCode(linkCode)) {
			Link link = linkService.getLinkByCode(linkCode);
			model.addAttribute("link", link);
			return "link_details";
		} else {
			model.addAttribute("error", "Link not exist!");
			return MainPageController.mainPage(model, null, null);
		}

	}

}
