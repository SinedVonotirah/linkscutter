package by.vonotirah.linkscutter.service.impl;

import java.util.HashSet;
import java.util.List;
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
	public void saveOrUpdate(Tag tag) {
		if (tag.getId() == null) {
			tagDao.insertEntity(tag);
		} else {
			tagDao.updateEntity(tag);
		}
	}

	@Override
	@Transactional
	public boolean tagExist(String name) {
		return tagDao.tagExist(name);
	}

	@Override
	@Transactional
	public Tag getTagByName(String name) {
		return tagDao.gatTagByName(name);
	}

	@Override
	@Transactional
	public Set<Tag> tagsProcessing(List<String> tags) {
		HashSet<Tag> tagSet = new HashSet<Tag>();
		for (String s : tags) {
			if (this.tagExist(s)) {
				tagSet.add(this.getTagByName(s));
			} else {
				Tag tag = new Tag();
				tag.setName(s);
				this.saveOrUpdate(tag);
				tagSet.add(tag);
			}
		}
		return tagSet;
	}

	@Override
	@Transactional
	public void deleteTagById(Long id) {
		tagDao.deleteEntityById(id);
	}

}
