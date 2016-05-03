package by.vonotirah.linkscutter.webapp.restcontrollers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.Link_;
import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.LinkDetailsService;
import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.service.UserService;
import by.vonotirah.linkscutter.service.exceptions.AccountExistsException;
import by.vonotirah.linkscutter.service.exceptions.LinkNotFoundException;
import by.vonotirah.linkscutter.webapp.exceptions.ConflictException;
import by.vonotirah.linkscutter.webapp.exceptions.NotFoundException;
import by.vonotirah.linkscutter.webapp.models.LinksModel;

@RestController
public class RestApiController {

	@Inject
	private LinkService linkService;

	@Inject
	private UserService userService;

	@Inject
	private LinkDetailsService linkDetailsService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

	@PreAuthorize("hasPermission('Link', 'getAllLinks')")
	@RequestMapping(value = "/links/", method = RequestMethod.GET, params = "page")
	public ResponseEntity<LinksModel> getAllLinks(@RequestParam("page") int page) {
		LOGGER.info("REST API --- getAllLinks paggination");

		UserAccount userAccount = getAuthUserAccount();
		LinksModel linksModel = new LinksModel();

		try {
			int startRecord = (page - 1) * 10;
			List<Link> links = linkService.getLinksByUser(userAccount, Link_.id, false, startRecord, 10);
			Long count = linkService.getLinksCountByUser(userAccount);
			linksModel.setLinks(links);
			linksModel.setCount(count);
			return new ResponseEntity<LinksModel>(linksModel, HttpStatus.OK);
		} catch (LinkNotFoundException exception) {
			throw new NotFoundException(exception);
		}
	}

	@PreAuthorize("hasPermission('Link', 'getAllLinks')")
	@RequestMapping(value = "/links/", method = RequestMethod.GET)
	public ResponseEntity<List<Link>> getAllLinks() {
		LOGGER.info("REST API --- getAllLinks");

		UserAccount userAccount = getAuthUserAccount();

		try {
			List<Link> links = linkService.getLinksByUser(userAccount);
			return new ResponseEntity<List<Link>>(links, HttpStatus.OK);
		} catch (LinkNotFoundException exception) {
			throw new NotFoundException(exception);
		}
	}

	@RequestMapping(value = "/tag/{tagId}", method = RequestMethod.GET, params = "page")
	public ResponseEntity<LinksModel> getLinksByTag(@PathVariable("tagId") final long tagId,
			@RequestParam("page") int page) {
		LOGGER.info("REST API --- getLinksByTag");
		System.out.println(tagId);
		System.out.println(page);

		LinksModel linksModel = new LinksModel();

		try {
			int startRecord = (page - 1) * 10;
			List<Link> links = linkService.getLinksByTag(tagId, Link_.id, false, startRecord, 10);
			Long count = linkService.getLinksCountByTag(tagId);
			linksModel.setLinks(links);
			linksModel.setCount(count);

			return new ResponseEntity<LinksModel>(linksModel, HttpStatus.OK);
		} catch (LinkNotFoundException exception) {
			throw new NotFoundException(exception);
		}
	}

	@RequestMapping(value = "/tag/{tagId}", method = RequestMethod.GET)
	public ResponseEntity<List<Link>> getLinksByTag(@PathVariable("tagId") final long tagId) {
		LOGGER.info("REST API --- getLinksByTag");

		try {
			List<Link> links = linkService.getLinksByTag(tagId);
			return new ResponseEntity<List<Link>>(links, HttpStatus.OK);
		} catch (LinkNotFoundException exception) {
			throw new NotFoundException(exception);
		}
	}

	@RequestMapping(value = "/link/{id}", method = RequestMethod.GET)
	public ResponseEntity<Link> getLinkById(@PathVariable("id") final long id) {
		LOGGER.info("REST API --- getLinkById");

		try {
			Link link = linkService.getLinkById(id);
			return new ResponseEntity<Link>(link, HttpStatus.OK);
		} catch (LinkNotFoundException exception) {
			throw new NotFoundException(exception);
		}

	}

	@PreAuthorize("hasPermission('Link', 'createLink')")
	@RequestMapping(value = "/links/", method = RequestMethod.POST)
	public ResponseEntity<Link> createLink(@Validated @RequestBody Link link) {
		LOGGER.info("REST API --- createLink");

		if (link.getUrl() == null || link.getUrl().length() == 0) {
			return new ResponseEntity<Link>(HttpStatus.NO_CONTENT);
		} else {
			UserAccount userAccount = getAuthUserAccount();
			Link createdLink = linkService.createNewLink(link, userAccount.getLogin());
			return new ResponseEntity<Link>(createdLink, HttpStatus.OK);
		}
	}

	@PreAuthorize("hasPermission('Link', 'deleteLink')")
	@RequestMapping(value = "/links/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Link> deleteLink(@PathVariable("id") final long id) {
		LOGGER.info("REST API --- deleteLink");

		UserAccount userAccount = getAuthUserAccount();

		try {
			Link link = linkService.getLinkById(id);
			if (!link.getUserAccount().equals(userAccount)) {
				return new ResponseEntity<Link>(HttpStatus.LOCKED);
			}
			linkService.deleteLinkById(id);
			return new ResponseEntity<Link>(HttpStatus.OK);
		} catch (LinkNotFoundException exception) {
			throw new NotFoundException();
		}
	}

	@PreAuthorize("hasPermission('Link', 'updateLink')")
	@RequestMapping(value = "/links/", method = RequestMethod.PUT)
	public ResponseEntity<Link> updateLink(@Valid @RequestBody final Link link) {
		LOGGER.info("REST API --- updateLink");

		if (link.getId() == null || link.getId() == 0L) {
			return new ResponseEntity<Link>(HttpStatus.BAD_REQUEST);
		}

		UserAccount userAccount = getAuthUserAccount();

		try {
			Link linkFromDB = linkService.getLinkById(link.getId());
			if (!linkFromDB.getUserAccount().equals(userAccount)) {
				return new ResponseEntity<Link>(HttpStatus.LOCKED);
			}
			linkDetailsService.updateLinkDetails(link);
			return new ResponseEntity<Link>(HttpStatus.OK);
		} catch (LinkNotFoundException exception) {
			throw new NotFoundException();
		}
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<UserAccount> createUser(@Valid @RequestBody final UserAccount user) {
		LOGGER.info("REST API --- createUser");

		try {
			UserAccount userAccount = userService.createNewUser(user);
			return new ResponseEntity<UserAccount>(userAccount, HttpStatus.OK);
		} catch (AccountExistsException exception) {
			throw new ConflictException(exception);
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<Link> searchLink(@RequestBody final String searchUrl) {
		LOGGER.info("REST API --- searchLink");

		try {
			String linkCode;
			linkCode = searchUrl.substring(searchUrl.length() - 6);
			Link link = linkService.getLinkByCode(linkCode);
			return new ResponseEntity<Link>(link, HttpStatus.OK);
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			return new ResponseEntity<Link>(HttpStatus.BAD_REQUEST);
		} catch (LinkNotFoundException exception) {
			throw new NotFoundException(exception);
		}
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public ResponseEntity<Object> redirectToUrl(@PathVariable("code") final String code) throws URISyntaxException {
		LOGGER.info("REST API --- redirectToUrl");

		URI uri;
		try {
			uri = linkService.linkRedirect(code);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(uri);
			return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
		} catch (NoResultException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

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

	private UserAccount getAuthUserAccount() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userLogin = auth.getName();
		return userService.getUserByLogin(userLogin);
	}

}
