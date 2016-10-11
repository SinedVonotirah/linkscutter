package by.vonotirah.linkscutter.service;

import java.util.Set;

import by.vonotirah.linkscutter.datamodel.Tag;

public interface TagService {

	// TODO delete
	void saveOrUpdate(Tag tag);

	// TODO delete
	boolean tagExist(String name);

	Tag getTagByName(String name);

	void deleteTagById(Long id);

	Set<Tag> tagsProcessing(Set<Tag> newTags);

}
