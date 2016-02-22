package by.vonotirah.linkscutter.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.vonotirah.linkscutter.dataaccess.LinkDao;
import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService {
	
	@Inject
	private LinkDao linkDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkServiceImpl.class);
	
	@Override
	@Transactional
	public void createNewLink(Link link){
		Validate.isTrue(link.getId() == null, "'createNewLink' method did not pass validation");
		linkDao.insertEntity(link);
		LOGGER.info("Link successfuly created");
	}
	
	@Override
	@Transactional
	public Link getLinkById(Long id){
		Link link = linkDao.getEntityById(id);
		LOGGER.info("Link with 'ID' - " +id+ " successfully extracted");
		return link;
	}
	
	@Override
	@Transactional
	public Link getLinkByCode(String code){
		Link link = linkDao.getLinkByCode(code);
		LOGGER.info("Link with 'GenCode' - " +code+ " successfully extracted");
		return link;
	}
}
