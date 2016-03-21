package by.vonotirah.linkscutter.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.vonotirah.linkscutter.dataaccess.LinkDao;
import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.LinkDetails;
import by.vonotirah.linkscutter.service.LinkDetailsService;
import by.vonotirah.linkscutter.service.LinkService;
import by.vonotirah.linkscutter.service.UserService;

@Service
public class LinkServiceImpl implements LinkService {

	@Inject
	private LinkDao linkDao;

	@Inject
	private LinkDetailsService linkDetailsService;

	@Inject
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(LinkServiceImpl.class);

	private static final int RANDOM_STRING_SIZE = 6;

	@Override
	@Transactional
	public void createNewLink(Link link) {
		Validate.isTrue(link.getId() == null, "'createNewLink' method did not pass validation");
		linkDao.insertEntity(link);
		LOGGER.info("Link successfuly created");
	}

	@Override
	@Transactional
	public Link createNewLink(String url, String login, String description, String tags) {
		final Link link = new Link();
		final LinkDetails linkDetails = linkDetailsService.createLinkDetails(description, tags);
		link.setLinkDetails(linkDetails);
		link.setUrl(url);
		link.setGenCode(generateLinkCode());
		link.setUserAccount(userService.getUserByLogin(login));
		createNewLink(link);
		return link;
	}

	@Override
	@Transactional
	public Link getLinkById(Long id) {
		Link link = linkDao.getEntityById(id);
		LOGGER.info("Link with 'ID' - " + id + " successfully extracted");
		return link;
	}

	@Override
	@Transactional
	public Link getLinkByCode(String code) {
		Link link = linkDao.getLinkByCode(code);
		LOGGER.info("Link with 'GenCode' - " + code + " successfully extracted");
		return link;
	}

	@Override
	@Transactional
	public URI linkRedirect(String code) throws URISyntaxException {
		final Link link = this.getLinkByCode(code);
		URI uri = new URI(link.getUrl());
		this.incRedirectCounter(link);
		return uri;
	}

	@Override
	@Transactional
	public boolean chekLinkExistByCode(String code) {
		return linkDao.checkCodeExist(code);
	}

	@Override
	@Transactional
	public List<Link> getLinksByTag(String tag) {
		return linkDao.getLinksByTag(tag);
	}

	@Transactional
	private void incRedirectCounter(Link link) {
		Long linkCounter = link.getLinkDetails().getCounter();
		linkCounter++;
		link.getLinkDetails().setCounter(linkCounter);
		linkDao.updateEntity(link);
	}

	private String generateLinkCode() {
		String genCode;
		do {
			genCode = RandomStringUtils.randomAlphabetic(RANDOM_STRING_SIZE);
		} while (linkDao.checkCodeExist(genCode));
		return genCode;
	}

}
