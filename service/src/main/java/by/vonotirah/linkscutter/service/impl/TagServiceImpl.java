package by.vonotirah.linkscutter.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.vonotirah.linkscutter.dataaccess.TagDao;
import by.vonotirah.linkscutter.datamodel.Tag;
import by.vonotirah.linkscutter.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Inject
	private TagDao tagDao;

	@Override
	@Transactional
	public Set<Tag> tagsProcessing(Set<Tag> newTags) {
		HashSet<Tag> tagSet = new HashSet<Tag>();
		for (Tag tag : newTags) {
			if (this.tagExist(tag.getName())) {
				tagSet.add(this.getTagByName(tag.getName()));
			} else {
				this.saveOrUpdate(tag);
				tagSet.add(tag);
			}
		}
		return tagSet;
	}

	// TODO private
	@Override
	@Transactional
	public void saveOrUpdate(Tag tag) {
		if (tag.getId() == null) {
			tagDao.insertEntity(tag);
		} else {
			tagDao.updateEntity(tag);
		}
	}

	// TODO private
	@Override
	@Transactional
	public boolean tagExist(String name) {
		return tagDao.tagExist(name);
	}

	@Override
	@Transactional
	public Tag getTagByName(String name) {
		return tagDao.getTagByName(name);
	}

	@Override
	@Transactional
	public void deleteTagById(Long id) {
		tagDao.deleteEntityById(id);
	}

}
