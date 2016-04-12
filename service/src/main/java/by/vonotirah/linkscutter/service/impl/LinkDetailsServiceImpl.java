package by.vonotirah.linkscutter.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.vonotirah.linkscutter.dataaccess.LinkDetailsDao;
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
	public LinkDetails createLinkDetails(String description, List<String> tags) {
		final LinkDetails linkDetails = new LinkDetails();
		linkDetails.setDescription(description);
		linkDetails.setCounter(0L);
		linkDetails.setCreated(new Date());
		linkDetails.setTags(tagService.tagsProcessing(tags));
		return linkDetails;
	}

	@Override
	@Transactional
	public void updateLinkDetails(Long id, String description, List<String> tags) {
		LinkDetails linkDetails = this.getLinkDetailsById(id);
		linkDetails.setDescription(description);
		linkDetails.setTags(tagService.tagsProcessing(tags));
		linkDetailsDao.updateEntity(linkDetails);
	}

	@Override
	@Transactional
	public LinkDetails getLinkDetailsById(Long id) {
		LinkDetails linkDetails = linkDetailsDao.getEntityById(id);
		return linkDetails;
	}
}
