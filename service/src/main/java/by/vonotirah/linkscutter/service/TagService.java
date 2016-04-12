package by.vonotirah.linkscutter.service;

import java.util.List;
import java.util.Set;

import by.vonotirah.linkscutter.datamodel.Tag;

public interface TagService {

	void saveOrUpdate(Tag tag);

	boolean tagExist(String name);

	Tag getTagByName(String name);

	Set<Tag> tagsProcessing(List<String> tags);

	void deleteTagById(Long id);

}
