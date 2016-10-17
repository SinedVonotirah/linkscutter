package by.vonotirah.linkscutter.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.vonotirah.linkscutter.dataaccess.LinkDao;
import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.LinkDetails;
import by.vonotirah.linkscutter.datamodel.UserAccount;
import by.vonotirah.linkscutter.service.LinkDetailsService;
import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.service.exceptions.LinkNotFoundException;

@Service
public class LinkServiceImpl implements LinkService {

	@Inject
	private LinkDao linkDao;

	@Inject
	private LinkDetailsService linkDetailsService;

	private static final Logger LOGGER = LoggerFactory.getLogger(LinkServiceImpl.class);

	private static final int RANDOM_STRING_SIZE = 6;

	@Override
	@Transactional
	public Link createNewLink(Link link, UserAccount userAccount) {
		final LinkDetails linkDetails = linkDetailsService.createLinkDetails(link);
		link.setLinkDetails(linkDetails);
		link.setUrl(urlVerification(link.getUrl()));
		link.setGenCode(generateLinkCode());
		link.setUserAccount(userAccount);
		linkDao.insertEntity(link);
		LOGGER.info("Link successfuly created");
		return link;
	}

	@Override
	@Transactional
	public List<Link> getLinksByUser(UserAccount userAccount) {
		List<Link> links = linkDao.getLinksByUser(userAccount);
		if (links.isEmpty()) {
			throw new LinkNotFoundException("Links by User with login" + userAccount.getLogin() + "not found");
		}
		LOGGER.info("Links by User - " + userAccount.getLogin() + " successfully extracted");
		return links;
	}

	@Override
	@Transactional
	public List<Link> getLinksByUser(UserAccount userAccount, SingularAttribute<Link, ?> attr, boolean ascending,
			int startRecord, int pageSize) {

		List<Link> links = linkDao.getLinksByUser(userAccount, attr, ascending, startRecord, pageSize);
		if (links.isEmpty()) {
			throw new LinkNotFoundException("Links by User with login" + userAccount.getLogin() + "not found");
		}
		LOGGER.info("Links by User - " + userAccount.getLogin() + " successfully extracted");
		return links;
	}

	@Override
	@Transactional
	public Long getLinksCountByUser(UserAccount userAccount) {
		Long count = linkDao.getLinksCountByUser(userAccount);
		if (count == null) {
			throw new LinkNotFoundException("Links by User with login" + userAccount.getLogin() + "not found");
		}
		LOGGER.info("LinksCount by User - " + userAccount.getLogin() + " successfully extracted");
		return count;
	}

	@Override
	@Transactional
	public Long getLinksCountByTag(Long tagId) {
		Long count = linkDao.getLinksCountByTag(tagId);
		if (count == null) {
			throw new LinkNotFoundException("Links by Tag with ID" + tagId + "not found");
		}
		LOGGER.info("LinksCount by TagID - " + tagId + " successfully extracted");
		return count;
	}

	@Override
	@Transactional
	public List<Link> getLinksByTag(Long tagId) {
		List<Link> links = linkDao.getLinksByTag(tagId);
		if (links.isEmpty()) {
			throw new LinkNotFoundException("Links by TagId - " + tagId + "not found");
		}
		return links;
	}

	@Override
	public List<Link> getLinksByTag(Long tagId, SingularAttribute<Link, ?> attr, boolean ascending, int startRecord,
			int pageSize) {

		List<Link> links = linkDao.getLinksByTag(tagId, attr, ascending, startRecord, pageSize);
		if (links.isEmpty()) {
			throw new LinkNotFoundException("Links by TagId - " + tagId + "not found");
		}
		return links;
	}

	@Override
	@Transactional
	public Link getLinkByCode(String code) {
		try {
			Link link = linkDao.getLinkByCode(code);
			LOGGER.info("Link with 'GenCode' - " + code + " successfully extracted");
			return link;
		} catch (NoResultException exception) {
			throw new LinkNotFoundException("Link with Code - " + code + " not found");
		}
	}

	@Override
	@Transactional
	public Link getLinkById(Long id) {
		Link link = linkDao.getEntityById(id);
		if (link == null) {
			throw new LinkNotFoundException("Link with Id - " + id + " not found");
		}
		LOGGER.info("Link with 'ID' - " + id + " successfully extracted");
		return link;
	}

	@Override
	@Transactional
	public void deleteLinkById(Long id) {
		linkDao.deleteEntityById(id);
	}

	@Override
	@Transactional
	public boolean chekLinkExistByCode(String code) {
		return linkDao.checkCodeExist(code);
	}

	// TODO separate class "Redirector" + interface
	@Override
	@Transactional
	public URI linkRedirect(String code) throws URISyntaxException {
		final Link link = getLinkByCode(code);
		URI uri = new URI(link.getUrl());
		this.incRedirectCounter(link);
		return uri;
	}

	// TODO separate class "Redirector" + interface
	@Transactional
	private void incRedirectCounter(Link link) {
		Long linkCounter = link.getLinkDetails().getCounter();
		linkCounter++;
		link.getLinkDetails().setCounter(linkCounter);
		linkDao.updateEntity(link);
	}

	// TODO separate class + interface
	@Transactional
	private String generateLinkCode() {
		String genCode;
		do {
			genCode = RandomStringUtils.randomAlphabetic(RANDOM_STRING_SIZE);
		} while (linkDao.checkCodeExist(genCode));
		return genCode;
	}

	// TODO separate class + interface
	private String urlVerification(String url) {
		try {
			URI uri = new URI(url);
			if (uri.getScheme() == null) {
				String protocol = "http://";
				url = protocol.concat(uri.toString());
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return url;
	}
}
