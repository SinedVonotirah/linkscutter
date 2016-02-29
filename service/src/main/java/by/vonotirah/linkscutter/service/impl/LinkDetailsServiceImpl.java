package by.vonotirah.linkscutter.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.vonotirah.linkscutter.dataaccess.LinkDetailsDao;
import by.vonotirah.linkscutter.datamodel.LinkDetails;
import by.vonotirah.linkscutter.service.LinkDetailsService;

@Service
public class LinkDetailsServiceImpl implements LinkDetailsService {

	@Inject
	private LinkDetailsDao linkDetailsDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkDetailsServiceImpl.class);
	
	@Override
	@Transactional
	public LinkDetails createLinkDetails(){
		LinkDetails linkDetails = new LinkDetails();
		linkDetails.setCreated(new Date());
		linkDetailsDao.insertEntity(linkDetails);
		LOGGER.info("LinkDetails successfuly created");
		return linkDetails;
	}
}
