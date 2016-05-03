package by.vonotirah.linkscutter.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.vonotirah.linkscutter.dataaccess.LinkDetailsDao;
import by.vonotirah.linkscutter.datamodel.Link;
import by.vonotirah.linkscutter.datamodel.LinkDetails;
import by.vonotirah.linkscutter.service.LinkDetailsService;
import by.vonotirah.linkscutter.service.TagService;

@Service
public class LinkDetailsServiceImpl implements LinkDetailsService {

	@Inject
	private LinkDetailsDao linkDetailsDao;

	@Inject
	private TagService tagService;

	@Override
	@Transactional
	public LinkDetails createLinkDetails(Link link) {
		LinkDetails linkDetails = new LinkDetails();
		linkDetails.setDescription(link.getLinkDetails().getDescription());
		linkDetails.setCounter(0L);
		linkDetails.setCreated(new Date());
		linkDetails.setTags(tagService.tagsProcessing(link.getLinkDetails().getTags()));
		return linkDetails;
	}

	@Override
	@Transactional
	public void updateLinkDetails(Link link) {
		LinkDetails linkDetails = this.getLinkDetailsById(link.getId());
		linkDetails.setDescription(link.getLinkDetails().getDescription());
		linkDetails.setTags(tagService.tagsProcessing(link.getLinkDetails().getTags()));
		linkDetailsDao.updateEntity(linkDetails);
	}

	@Override
	@Transactional
	public LinkDetails getLinkDetailsById(Long id) {
		LinkDetails linkDetails = linkDetailsDao.getEntityById(id);
		return linkDetails;
	}
}
