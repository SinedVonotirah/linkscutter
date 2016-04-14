package by.vonotirah.linkscutter.webapp.restcontrollers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.LinkDetailsService;
import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.service.UserService;
import by.vonotirah.linkscutter.webapp.models.LinkModel;

@RestController
public class RestApiController {

	@Inject
	private LinkService linkService;

	@Inject
	private UserService userService;

	@Inject
	private LinkDetailsService linkDetailsService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

	@PreAuthorize("hasPermission('Links', 'getAllLinks')")
	@RequestMapping(value = "/links/", method = RequestMethod.GET)
	public ResponseEntity<List<Link>> getAllLinks() {
		LOGGER.info("REST API --- getAllLinks");

		UserAccount userAccount = getAuthUserAccount();

		List<Link> links = linkService.getLinksByUser(userAccount);
		if (links.isEmpty()) {
			return new ResponseEntity<List<Link>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Link>>(links, HttpStatus.OK);
	}

	@PreAuthorize("hasPermission('Links', 'createLink')")
	@RequestMapping(value = "/links/", method = RequestMethod.POST)
	public ResponseEntity<Link> createLink(@RequestBody LinkModel link) {

		LOGGER.info("REST API --- createLink");

		if (link.getUrl() == null || link.getUrl().length() == 0) {
			return new ResponseEntity<Link>(HttpStatus.NO_CONTENT);
		} else {
			UserAccount userAccount = getAuthUserAccount();
			Link createdLink = linkService.createNewLink(link.getUrl(), userAccount.getLogin(), link.getDescription(),
					link.getTags());
			return new ResponseEntity<Link>(createdLink, HttpStatus.OK);
		}
	}

	@PreAuthorize("hasPermission('Links', 'deleteLink')")
	@RequestMapping(value = "/links/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Link> deleteLink(@PathVariable("id") final long id) {

		LOGGER.info("REST API --- deleteLink");

		UserAccount userAccount = getAuthUserAccount();
		Link link = linkService.getLinkById(id);
		if (link == null) {
			return new ResponseEntity<Link>(HttpStatus.NOT_FOUND);
		}
		if (!link.getUserAccount().equals(userAccount)) {
			return new ResponseEntity<Link>(HttpStatus.LOCKED);
		}
		linkService.deleteLinkById(id);
		return new ResponseEntity<Link>(HttpStatus.OK);
	}

	@RequestMapping(value = "/link/{id}", method = RequestMethod.GET)
	public ResponseEntity<Link> getLinkById(@PathVariable("id") final long id) {

		LOGGER.info("REST API --- getLinkById");

		Link link = linkService.getLinkById(id);
		if (link == null) {
			return new ResponseEntity<Link>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Link>(link, HttpStatus.OK);
		}

	}

	@PreAuthorize("hasPermission('Links', 'updateLink')")
	@RequestMapping(value = "/links/", method = RequestMethod.PUT)
	public ResponseEntity<Link> updateLink(@RequestBody final LinkModel linkModel) {

		LOGGER.info("REST API --- updateLink");

		if (linkModel.getId() == null || linkModel.getId() == 0L) {
			return new ResponseEntity<Link>(HttpStatus.BAD_REQUEST);
		}

		UserAccount userAccount = getAuthUserAccount();
		Link link = linkService.getLinkById(linkModel.getId());
		if (!link.getUserAccount().equals(userAccount)) {
			return new ResponseEntity<Link>(HttpStatus.LOCKED);
		}

		linkDetailsService.updateLinkDetails(linkModel.getId(), linkModel.getDescription(), linkModel.getTags());

		return new ResponseEntity<Link>(HttpStatus.OK);
	}

	@RequestMapping(value = "/tag/{tagId}", method = RequestMethod.GET)
	public ResponseEntity<List<Link>> getLinksByTag(@PathVariable("tagId") final long tagId) {

		LOGGER.info("REST API --- getLinksByTag");

		List<Link> links = linkService.getLinksByTag(tagId);
		if (links.isEmpty()) {

			return new ResponseEntity<List<Link>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Link>>(links, HttpStatus.OK);
	}

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public ResponseEntity<Object> isAuth() {

		LOGGER.info("REST API --- isAuth");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<UserAccount> createUser(@RequestBody final UserAccount user) {

		LOGGER.info("REST API --- createUser");

		if (userService.chekUserLoginExist(user)) {
			return new ResponseEntity<UserAccount>(HttpStatus.BAD_REQUEST);
		}
		if (userService.chekUserMailExist(user)) {
			return new ResponseEntity<UserAccount>(HttpStatus.BAD_REQUEST);
		} else {
			UserAccount userAccount = userService.userRegistration(user);
			return new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public ResponseEntity<Object> redirectToUrl(@PathVariable("code") final String code) throws URISyntaxException {

		LOGGER.info("REST API --- redirectToUrl");

		URI uri;
		try {
			uri = linkService.linkRedirect(code);
		} catch (NoResultException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<Link> searchLink(@RequestBody final String searchUrl) {

		LOGGER.info("REST API --- searchLink");

		String linkCode;
		try {
			linkCode = searchUrl.substring(searchUrl.length() - 6);
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			return new ResponseEntity<Link>(HttpStatus.BAD_REQUEST);
		}
		if (linkService.chekLinkExistByCode(linkCode)) {
			Link link = linkService.getLinkByCode(linkCode);
			return new ResponseEntity<Link>(link, HttpStatus.OK);
		} else {
			return new ResponseEntity<Link>(HttpStatus.NOT_FOUND);
		}
	}

	private UserAccount getAuthUserAccount() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userLogin = auth.getName();
		return userService.getUserByLogin(userLogin);
	}

}
