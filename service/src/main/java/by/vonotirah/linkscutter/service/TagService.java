package by.vonotirah.linkscutter.service;

import java.util.Set;

import by.vonotirah.linkscutter.datamodel.Tag;

public interface TagService {

	void saveOrUpdate(Tag tag);
	
	boolean tagExist (String name);
	
	Tag getTagByName (String name);

	Set<Tag> tagsProcessing(String tags);
}
