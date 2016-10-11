package by.vonotirah.linkscutter.webapp.restcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

	@RequestMapping(value = "/login")
	public String getLoginTemplate() {
		return "login";
	}

	@RequestMapping(value = "/registration")
	public String getRegistrationTemplate() {
		return "registration";
	}

	@RequestMapping(value = "/links")
	public String getLinksTemplate() {
		return "links";
	}

	@RequestMapping(value = "/details")
	public String getDetailsTemplate() {
		return "linkdetails";
	}

	@RequestMapping(value = "/link/{linkId}/{tagId}")
	public String getComputersDetailTemplate(@PathVariable("linkId") long linkId, @PathVariable("tagId") long tagId) {
		return "linksbytag";
	}

}
